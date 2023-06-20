package com.example.breakingworldnewsapp.data.di

import com.example.breakingworldnewsapp.data.WorldNewsRepositoryImpl
import com.example.breakingworldnewsapp.data.remote.ApiService
import com.example.breakingworldnewsapp.domain.WorldNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideDeviceApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideWorldNewsRepository(apiService: ApiService): WorldNewsRepository {
        return WorldNewsRepositoryImpl(apiService)
    }
}