package com.example.breakingworldnewsapp.data

import android.app.Application
import com.example.breakingworldnewsapp.data.api.ApiFactory
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

data class WorldNewsRepositoryImpl(val application: Application) : WorldNewsRepository {

    private val apiFactory = ApiFactory

    override suspend fun getWorldNews(): Result<WorldNewsModel> {
        return runCatching {
            apiFactory.apiService.getTopUsNews()
        }
    }
}