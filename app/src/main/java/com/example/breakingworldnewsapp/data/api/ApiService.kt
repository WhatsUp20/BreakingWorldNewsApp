package com.example.breakingworldnewsapp.data.api

import com.example.breakingworldnewsapp.domain.models.WorldNewsModel
import retrofit2.http.GET

interface ApiService {
    @GET("news?apikey=pub_235499741d7ff1ca87d71968107af9e65ed2f&country=ru")
    suspend fun getRussianNews(): WorldNewsModel

    @GET("news?apikey=pub_218026a9331ba3df1f082421c3c0af2b907c0&country=us")
    suspend fun getAmericanNews(): WorldNewsModel
}