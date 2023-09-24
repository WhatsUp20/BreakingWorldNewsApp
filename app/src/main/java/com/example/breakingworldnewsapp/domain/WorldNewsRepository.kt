package com.example.breakingworldnewsapp.domain

import com.example.breakingworldnewsapp.domain.models.ResultsModel
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import kotlinx.coroutines.flow.StateFlow

interface WorldNewsRepository {

    suspend fun getWorldNews(): Result<WorldNewsModel>

    fun getWorldNewsFromDb(): StateFlow<List<ResultsModel>>
}