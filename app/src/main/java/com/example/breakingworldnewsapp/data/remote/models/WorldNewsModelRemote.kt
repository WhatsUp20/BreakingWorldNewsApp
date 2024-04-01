package com.example.breakingworldnewsapp.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorldNewsModelRemote(

    @SerializedName("articles")
    @Expose
    val resultsModelsRemote: List<ResultsModelRemote>
)
