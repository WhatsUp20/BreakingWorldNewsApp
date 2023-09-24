package com.example.breakingworldnewsapp.data.di

import com.example.breakingworldnewsapp.data.WorldNewsRepositoryImpl
import com.example.breakingworldnewsapp.data.remote.ApiService
import com.example.breakingworldnewsapp.data.remote.LocalWorldNewsStore
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideWorldNewsRepository(apiService: ApiService, localWorldNewsStore: LocalWorldNewsStore): WorldNewsRepository {
        return WorldNewsRepositoryImpl(apiService, localWorldNewsStore)
    }
}