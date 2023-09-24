package com.example.breakingworldnewsapp.data.local

import com.example.breakingworldnewsapp.data.database.WorldNewsInfoDao
import com.example.breakingworldnewsapp.data.remote.LocalWorldNewsStore
import com.example.breakingworldnewsapp.domain.models.ResultsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWorldNewsStoreImpl @Inject constructor(
    private val worldNewsInfoDao: WorldNewsInfoDao
) : LocalWorldNewsStore {

    private val _worldNewsFlow = MutableStateFlow(emptyList<ResultsModel>())
    private val worldNewsFlow: StateFlow<List<ResultsModel>> = _worldNewsFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getAllWorldNews(): StateFlow<List<ResultsModel>> {
        coroutineScope.launch {
            worldNewsInfoDao.getWorldNewsList().flowOn(Dispatchers.IO).collect { worldNewsList ->
                _worldNewsFlow.update { worldNewsList }
            }
        }

        return worldNewsFlow
    }

    override suspend fun deleteAllWorldNews(): Int {
        return worldNewsInfoDao.clearWorldNewsList()
    }

    override suspend fun saveAllWorldNews(worldNewsList: List<ResultsModel>) {
        coroutineScope.launch {
            worldNewsInfoDao.insertWorldNewsList(worldNewsList)
        }
    }
}