package com.example.breakingworldnewsapp.presentation.ui.welcome

import com.example.breakingworldnewsapp.domain.models.ResultsModel

internal data class WelcomeScreenViewState(
    val worldNewsList: List<ResultsModel> = emptyList(),
    val peekProgress: Boolean = false,
    val events: List<Event> = emptyList(),
) {

    sealed interface Event {

        class LoadFailure(val throwable: Throwable): Event
    }
}