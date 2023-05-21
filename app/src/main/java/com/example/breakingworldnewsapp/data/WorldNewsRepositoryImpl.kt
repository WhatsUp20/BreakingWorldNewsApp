package com.example.breakingworldnewsapp.data

import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

object WorldNewsRepositoryImpl: WorldNewsRepository {

    override suspend fun getWorldNews(): Result<WorldNewsModel> {
        TODO()
    }
}