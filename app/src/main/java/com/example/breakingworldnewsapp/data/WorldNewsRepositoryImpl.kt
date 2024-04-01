package com.example.breakingworldnewsapp.data

import com.example.breakingworldnewsapp.data.local.LocalWorldNewsStore
import com.example.breakingworldnewsapp.data.mapper.toModel
import com.example.breakingworldnewsapp.data.mapper.toRemote
import com.example.breakingworldnewsapp.data.remote.ApiService
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import com.example.breakingworldnewsapp.domain.models.ResultsModel
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorldNewsRepositoryImpl @Inject constructor(
    private val apiFactory: ApiService,
    private val localWorldNewsStore: LocalWorldNewsStore
) : WorldNewsRepository {

    override suspend fun getWorldNews(): Result<WorldNewsModel> {
        return runCatching {
            apiFactory.getTopNews().toModel()
        }.onSuccess { result ->
            saveNews(result)
        }.onFailure {
           Timber.tag(LOG_TAG).w(it, "Failed to save data to local Db")
        }
    }

    override suspend fun getWorldNewsFromDb(): Flow<List<ResultsModel>> {
        return localWorldNewsStore.getAllWorldNews().map { remotes -> remotes.map { it.toModel() } }
    }

    private suspend fun saveNews(worldNewsModel: WorldNewsModel) {
        localWorldNewsStore.deleteAllWorldNews()
        localWorldNewsStore.saveAllWorldNews(worldNewsModel.resultsModels.map { it.toRemote() })
    }
}

private const val LOG_TAG = "WorldNewsRepository"