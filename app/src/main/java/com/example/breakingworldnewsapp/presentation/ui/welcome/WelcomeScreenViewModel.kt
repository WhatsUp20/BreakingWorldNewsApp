package com.example.breakingworldnewsapp.presentation.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingworldnewsapp.domain.GetWorldNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val getWorldNewsUseCase: GetWorldNewsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeScreenViewState())
    val viewState = _viewState.asStateFlow()

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
                    _viewState.update { it.copy(worldNewsList = resultNews.resultsModels) }
                }
                .onFailure { throwable ->
                    getAllNewsFromDB(this, throwable)
                }
            _viewState.update { it.copy(peekProgress = false) }
        }
    }

    private fun getAllNewsFromDB(coroutineScope: CoroutineScope, throwable: Throwable) {
        coroutineScope.launch(Dispatchers.IO) {
            getWorldNewsUseCase.getWorldNewsFromDb().collect { news ->
                _viewState.update { it.copy(
                    worldNewsList = news,
                    events = it.events + WelcomeScreenViewState.Event.LoadFailure(throwable)
                ) }
            }
        }
    }
}