package com.example.breakingworldnewsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.breakingworldnewsapp.domain.models.ResultsModel

@Database(entities = [ResultsModel::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun worldNewsInfoDao(): WorldNewsInfoDao
}

private const val DATABASE_VERSION = 1