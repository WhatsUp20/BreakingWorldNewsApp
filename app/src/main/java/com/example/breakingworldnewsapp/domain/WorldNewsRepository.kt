package com.example.breakingworldnewsapp.domain

import com.example.breakingworldnewsapp.domain.models.ResultsModel
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import kotlinx.coroutines.flow.Flow

interface WorldNewsRepository {

    suspend fun getWorldNews(): Result<WorldNewsModel>

    suspend fun getWorldNewsFromDb(): Flow<List<ResultsModel>>
}