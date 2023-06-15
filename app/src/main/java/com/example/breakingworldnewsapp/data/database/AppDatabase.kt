package com.example.breakingworldnewsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.breakingworldnewsapp.domain.models.ResultsModel

@Database(entities = [ResultsModel::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = DATABASE_NAME

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                db?.let { return it }
                val instance: AppDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun worldNewsInfoDao(): WorldNewsInfoDao
}

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "WorldNewsDB"