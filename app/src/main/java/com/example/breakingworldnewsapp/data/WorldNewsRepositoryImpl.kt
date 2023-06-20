package com.example.breakingworldnewsapp.data

import com.example.breakingworldnewsapp.data.remote.ApiService
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorldNewsRepositoryImpl @Inject constructor(
    private val apiFactory: ApiService
) : WorldNewsRepository {

    override suspend fun getWorldNews(): Result<WorldNewsModel> {
        return runCatching {
            apiFactory.getTopUsNews()
        }
    }
}