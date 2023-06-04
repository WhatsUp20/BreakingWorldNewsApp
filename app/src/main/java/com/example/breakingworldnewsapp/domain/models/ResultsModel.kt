package com.example.breakingworldnewsapp.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultsModel(

    @SerializedName("source")
    @Expose
    val sourceModel: SourceModel,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("urlToImage")
    @Expose
    val imageUrl: String? = null,

    @SerializedName("language")
    @Expose
    val language: String,

    @SerializedName("link")
    @Expose
    val link: String,
)

data class SourceModel(

    @SerializedName("name")
    @Expose
    val name: String
)