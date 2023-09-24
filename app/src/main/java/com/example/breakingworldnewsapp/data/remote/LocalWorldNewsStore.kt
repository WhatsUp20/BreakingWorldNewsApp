package com.example.breakingworldnewsapp.data.remote

import com.example.breakingworldnewsapp.domain.models.ResultsModel
import kotlinx.coroutines.flow.StateFlow

interface LocalWorldNewsStore {

    fun getAllWorldNews(): StateFlow<List<ResultsModel>>

    suspend fun deleteAllWorldNews(): Int

    suspend fun saveAllWorldNews(worldNewsList: List<ResultsModel>)
}