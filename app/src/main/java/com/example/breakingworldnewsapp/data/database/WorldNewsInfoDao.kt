package com.example.breakingworldnewsapp.data.database

import androidx.room.*
import com.example.breakingworldnewsapp.data.remote.models.ResultsModelRemote
import kotlinx.coroutines.flow.Flow

@Dao
interface WorldNewsInfoDao {

    @Query("SELECT * FROM world_news_list")
    fun getWorldNewsList(): Flow<List<ResultsModelRemote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorldNewsList(worldNewsList: List<ResultsModelRemote>)

    @Query("DELETE FROM world_news_list")
    suspend fun clearWorldNewsList(): Int
}