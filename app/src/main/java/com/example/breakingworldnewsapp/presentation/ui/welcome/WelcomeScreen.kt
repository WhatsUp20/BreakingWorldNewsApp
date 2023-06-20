package com.example.breakingworldnewsapp.presentation.ui.welcome

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.breakingworldnewsapp.R
import com.example.breakingworldnewsapp.presentation.ui.commonViews.TopicsItem

@Composable
fun WelcomeScreen(
    viewModel: WelcomeScreenViewModel = hiltViewModel(),
    setArgs: (imageUrl: String?, title: String, fullDesc: String, source: String) -> Unit
) {

    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewState.events) {
        viewState.events.firstOrNull()?.let { event ->
            when (event) {
                is WelcomeScreenViewState.Event.LoadFailure -> {
                    Toast.makeText(context, "Error:  ${event.throwable}", Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.nextEvent()
    }

    Column(
        Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if (viewState.peekProgress) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator()
            }
        }
        Column {
            Column(Modifier.padding(horizontal = 12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    TopicsItem(name = "CNN")
                    TopicsItem(name = "Reuters")
                    TopicsItem(name = "CBS News")
                    TopicsItem(name = "Associated Press")
                    TopicsItem(name = "Fox Business")
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            itemsIndexed(viewState.worldNewsList) { _, item ->
                Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = if (item.imageUrl.isNullOrBlank()) {
                                painterResource(id = R.drawable.placeholder)
                            } else {
                                rememberAsyncImagePainter(item.imageUrl)
                            },
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clickable {
                                    setArgs(
                                        item.imageUrl,
                                        item.title,
                                        item.description ?: " ",
                                        item.source.name
                                    )
                                }
                        )
                    }
                    Text(
                        text = item.title,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 12.dp, top = 12.dp, start = 8.dp)
                    )
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen() { _, _, _, _ -> }
}