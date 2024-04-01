package com.example.breakingworldnewsapp.domain.models

data class ResultsModel(
    var id: Long = 0,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String? = null,
    val link: String,
    val source: SourceModel
)

data class SourceModel(
    val name: String
)