package com.example.breakingworldnewsapp.data.local

import com.example.breakingworldnewsapp.data.database.WorldNewsInfoDao
import com.example.breakingworldnewsapp.data.remote.models.ResultsModelRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWorldNewsStoreImpl @Inject constructor(
    private val worldNewsInfoDao: WorldNewsInfoDao
) : LocalWorldNewsStore {

    override suspend fun getAllWorldNews(): Flow<List<ResultsModelRemote>> {
        return worldNewsInfoDao.getWorldNewsList().flowOn(Dispatchers.IO)
    }

    override suspend fun deleteAllWorldNews(): Int {
        return worldNewsInfoDao.clearWorldNewsList()
    }

    override suspend fun saveAllWorldNews(worldNewsList: List<ResultsModelRemote>) {
        worldNewsInfoDao.insertWorldNewsList(worldNewsList)
    }
}