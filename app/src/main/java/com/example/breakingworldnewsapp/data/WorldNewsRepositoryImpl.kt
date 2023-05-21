package com.example.breakingworldnewsapp.data

import com.example.breakingworldnewsapp.data.api.ApiFactory
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

object WorldNewsRepositoryImpl: WorldNewsRepository {

    private val apiFactory = ApiFactory

    override suspend fun getWorldNews(): Result<WorldNewsModel> {
        return runCatching {
            apiFactory.apiService.getRussianNews()
        }
    }
}