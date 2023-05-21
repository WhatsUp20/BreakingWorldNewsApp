package com.example.breakingworldnewsapp.presentation.ui.welcome

import com.example.breakingworldnewsapp.domain.models.ResultsModel

internal data class WelcomeScreenViewState(
    val worldNewsList: List<ResultsModel> = emptyList(),
    val events: List<Event> = emptyList(),
) {

    sealed interface Event {

        object LoadSuccessful : Event
        class LoadFailure(val throwable: Throwable): Event
    }
}