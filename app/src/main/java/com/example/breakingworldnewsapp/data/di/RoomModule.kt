package com.example.breakingworldnewsapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.breakingworldnewsapp.data.database.AppDatabase
import com.example.breakingworldnewsapp.data.database.WorldNewsInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DB_NAME = "WorldNewsDB"

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideWorldNewsInfoDao(appDatabase: AppDatabase): WorldNewsInfoDao {
        return appDatabase.worldNewsInfoDao()
    }
}