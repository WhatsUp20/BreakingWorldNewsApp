package com.example.breakingworldnewsapp.data.database

import androidx.room.*
import com.example.breakingworldnewsapp.domain.models.ResultsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WorldNewsInfoDao {

    @Query("SELECT * FROM world_news_list")
    fun getWorldNewsList(): Flow<List<ResultsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorldNewsList(worldNewsList: List<ResultsModel>)

    @Query("DELETE FROM world_news_list")
    suspend fun clearWorldNewsList(): Int
}