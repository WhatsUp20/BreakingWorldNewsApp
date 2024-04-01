package com.example.breakingworldnewsapp.data.local

import com.example.breakingworldnewsapp.data.remote.models.ResultsModelRemote
import kotlinx.coroutines.flow.Flow

interface LocalWorldNewsStore {

    suspend fun getAllWorldNews(): Flow<List<ResultsModelRemote>>

    suspend fun deleteAllWorldNews(): Int

    suspend fun saveAllWorldNews(worldNewsList: List<ResultsModelRemote>)
}