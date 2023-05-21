package com.example.breakingworldnewsapp.presentation.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingworldnewsapp.data.WorldNewsRepositoryImpl
import com.example.breakingworldnewsapp.domain.GetWorldNewsUseCase
import kotlinx.coroutines.launch

class WelcomeScreenViewModel: ViewModel() {

    private val repositoryByData = WorldNewsRepositoryImpl

    private val getWorldNewsUseCase = GetWorldNewsUseCase(repositoryByData)

    fun getWorldNews() {
        viewModelScope.launch { getWorldNewsUseCase.getWorldNewsList() }
    }
}