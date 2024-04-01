package com.example.breakingworldnewsapp.domain

import com.example.breakingworldnewsapp.domain.models.ResultsModel
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import kotlinx.coroutines.flow.Flow

class GetWorldNewsUseCase(private val worldNewsRepository: WorldNewsRepository) {

    suspend fun getWorldNewsList(): Result<WorldNewsModel> {
        return worldNewsRepository.getWorldNews()
    }

     suspend fun getWorldNewsFromDb(): Flow<List<ResultsModel>> {
        return worldNewsRepository.getWorldNewsFromDb()
    }
}