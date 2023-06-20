package com.example.breakingworldnewsapp.domain.di

import com.example.breakingworldnewsapp.domain.GetWorldNewsUseCase
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideWorldNewsUseCase(worldNewsRepository: WorldNewsRepository): GetWorldNewsUseCase {
        return GetWorldNewsUseCase(worldNewsRepository)
    }
}