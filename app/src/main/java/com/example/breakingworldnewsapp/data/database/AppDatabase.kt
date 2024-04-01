package com.example.breakingworldnewsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.breakingworldnewsapp.data.remote.models.ResultsModelRemote

@Database(entities = [ResultsModelRemote::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun worldNewsInfoDao(): WorldNewsInfoDao
}

private const val DATABASE_VERSION = 1