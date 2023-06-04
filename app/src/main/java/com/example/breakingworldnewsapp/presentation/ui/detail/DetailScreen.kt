package com.example.breakingworldnewsapp.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.breakingworldnewsapp.R
import com.example.breakingworldnewsapp.presentation.ui.commonViews.TopicsItem

@Composable
internal fun DetailScreen(
    image: String? = null,
    sourceName: String,
    title: String,
    fullDesc: String,
    onBackAction: () -> Unit
) {
    DetailScreenContent(image, sourceName, title, fullDesc, onBackAction)
}

@Composable
private fun DetailScreenContent(
    image: String? = null,
    sourceName: String,
    title: String,
    fullDesc: String,
    onBackAction: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = if (image.isNullOrBlank()) {
                    painterResource(id = R.drawable.placeholder)
                } else {
                    rememberAsyncImagePainter(image)
                },
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .size(40.dp)
                    .clickable { onBackAction() }
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row() {
                TopicsItem(name = sourceName)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null, tint = Color.DarkGray,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(28.dp)
                    )
                }
            }
            Text(text = title, fontSize = 24.sp)
            Text(text = fullDesc, fontSize = 17.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        image = "",
        sourceName = "Source Title",
        title = "News Title",
        fullDesc = "News Description",
    ) { }
}