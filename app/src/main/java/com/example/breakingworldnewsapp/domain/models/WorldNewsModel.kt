package com.example.breakingworldnewsapp.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorldNewsModel(

    @SerializedName("articles")
    @Expose
    val resultsModels: List<ResultsModel>
)