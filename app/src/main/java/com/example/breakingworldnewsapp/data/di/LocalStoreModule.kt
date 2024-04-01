package com.example.breakingworldnewsapp.data.di

import com.example.breakingworldnewsapp.data.local.LocalWorldNewsStore
import com.example.breakingworldnewsapp.data.local.LocalWorldNewsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStoreModule {

    @Binds
    @Singleton
    abstract fun bindLocalWorldNewsStore(localWorldNewsStoreImpl: LocalWorldNewsStoreImpl) : LocalWorldNewsStore
}