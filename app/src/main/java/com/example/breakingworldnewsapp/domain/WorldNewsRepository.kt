package com.example.breakingworldnewsapp.domain

import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

interface WorldNewsRepository {

    suspend fun getWorldNews(): Result<WorldNewsModel>
}