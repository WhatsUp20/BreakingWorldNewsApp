package com.example.breakingworldnewsapp.presentation.ui.welcome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingworldnewsapp.data.WorldNewsRepositoryImpl
import com.example.breakingworldnewsapp.data.database.AppDatabase
import com.example.breakingworldnewsapp.domain.GetWorldNewsUseCase
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

internal class WelcomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _viewState = MutableStateFlow(WelcomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    private val repositoryByData = WorldNewsRepositoryImpl(application)
    private val getWorldNewsUseCase = GetWorldNewsUseCase(repositoryByData)

    private val db = AppDatabase.getInstance(application)
    private val worldNewsList = db.worldNewsInfoDao()

    init {
        loadWorldNews()
    }

    fun nextEvent() {
        _viewState.update {
            it.copy(events = it.events.drop(1))
        }
    }

    private fun loadWorldNews() {
        viewModelScope.launch {
            _viewState.update { it.copy(peekProgress = true) }
            getWorldNewsUseCase.getWorldNewsList()
                .onSuccess { resultNews ->
                    worldNewsList.clearWorldNewsList()
                    insertResultsInDB(result = resultNews, this)
                    getAllNewsFromDB(this)
                }
                .onFailure { throwable ->
                    if (throwable is IOException) getAllNewsFromDB(this)
                    _viewState.update { it.copy(events = it.events + WelcomeScreenViewState.Event.LoadFailure(throwable)) }
                }
            _viewState.update { it.copy(peekProgress = false) }
        }
    }

    private fun insertResultsInDB(result: WorldNewsModel, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            worldNewsList.insertWorldNewsList(result.resultsModels)
        }
    }

    private fun getAllNewsFromDB(coroutineScope: CoroutineScope) {
        val job = coroutineScope.launch(Dispatchers.IO) {
            worldNewsList.getWorldNewsList().cancellable().collect { news ->
                _viewState.update { it.copy(worldNewsList = news) }
            }
        }
        if (_viewState.value.worldNewsList.isNotEmpty()) job.cancel()
    }
}