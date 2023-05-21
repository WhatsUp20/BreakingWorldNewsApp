package com.example.breakingworldnewsapp.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultsModel(

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("full_description")
    @Expose
    val fullDescription: String? = null,

    @SerializedName("content")
    @Expose
    val content: String? = null,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null
)