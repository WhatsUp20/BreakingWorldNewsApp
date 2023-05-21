package com.example.breakingworldnewsapp.presentation.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingworldnewsapp.data.WorldNewsRepositoryImpl
import com.example.breakingworldnewsapp.domain.GetWorldNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class WelcomeScreenViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    private val repositoryByData = WorldNewsRepositoryImpl

    private val getWorldNewsUseCase = GetWorldNewsUseCase(repositoryByData)

    fun nextEvent() {
        _viewState.update {
            it.copy(events = it.events.drop(1))
        }
    }

    fun getWorldNews() {
        viewModelScope.launch {
            getWorldNewsUseCase.getWorldNewsList()
                .onSuccess { result ->
                    _viewState.update {
                        it.copy(
                            worldNewsList = result.resultsModels,
                            events = it.events + WelcomeScreenViewState.Event.LoadSuccessful
                        )
                    }
                }
                .onFailure { throwable ->
                    _viewState.update { it.copy(events = it.events + WelcomeScreenViewState.Event.LoadFailure(throwable)) }
                }
        }
    }
}