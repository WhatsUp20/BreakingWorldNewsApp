package com.example.breakingworldnewsapp.data.remote

import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=us&apiKey=dd28980f826b457a926c3362f6012c7e")
    suspend fun getTopUsNews(): WorldNewsModel
}