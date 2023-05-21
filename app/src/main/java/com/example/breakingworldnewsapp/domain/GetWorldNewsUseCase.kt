package com.example.breakingworldnewsapp.domain

import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

class GetWorldNewsUseCase(private val worldNewsRepository: WorldNewsRepository) {

    suspend fun getWorldNewsList(): Result<WorldNewsModel> {
        return worldNewsRepository.getWorldNews()
    }
}