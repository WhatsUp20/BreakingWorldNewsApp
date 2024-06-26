package com.example.breakingworldnewsapp.data.remote.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "world_news_list")
data class ResultsModelRemote(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("content")
    @Expose
    val content: String? = null,

    @SerializedName("urlToImage")
    @Expose
    val imageUrl: String? = null,

    @SerializedName("link")
    @Expose
    val link: String? = null,

    @SerializedName("source")
    @Embedded
    @Expose
    val source: SourceModelRemote
)

data class SourceModelRemote(
    @SerializedName("name")
    @Expose
    val name: String
)
