package com.example.breakingworldnewsapp.data.mapper

import com.example.breakingworldnewsapp.data.remote.models.ResultsModelRemote
import com.example.breakingworldnewsapp.data.remote.models.SourceModelRemote
import com.example.breakingworldnewsapp.data.remote.models.WorldNewsModelRemote
import com.example.breakingworldnewsapp.domain.models.ResultsModel
import com.example.breakingworldnewsapp.domain.models.SourceModel
import com.example.breakingworldnewsapp.domain.models.WorldNewsModel

fun WorldNewsModelRemote.toModel(): WorldNewsModel {
    return WorldNewsModel(
        resultsModels = resultsModelsRemote.map { it.toModel() }
    )
}

fun ResultsModelRemote.toModel(): ResultsModel {
    return ResultsModel(
        id = id,
        title = title,
        description = description ?: " ",
        content = content.orEmpty(),
        imageUrl = imageUrl,
        link = link.orEmpty(),
        source = source.toModel()
    )
}

private fun SourceModelRemote.toModel(): SourceModel {
    return SourceModel(name = name)
}

fun ResultsModel.toRemote(): ResultsModelRemote {
    return ResultsModelRemote(
        id = id,
        title = title,
        description = description,
        content = content,
        imageUrl = imageUrl,
        link = link,
        source = source.toRemote()
    )
}

private fun SourceModel.toRemote(): SourceModelRemote {
    return SourceModelRemote(name = name)
}