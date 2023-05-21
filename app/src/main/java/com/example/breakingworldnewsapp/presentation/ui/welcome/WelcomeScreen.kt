package com.example.breakingworldnewsapp.presentation.ui.welcome

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {

    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewState.events) {
        viewState.events.firstOrNull()?.let { event ->
            when (event) {
                WelcomeScreenViewState.Event.LoadSuccessful -> {
                    Toast.makeText(context, "Load Successful", Toast.LENGTH_SHORT).show()
                }
                is WelcomeScreenViewState.Event.LoadFailure -> {
                    Toast.makeText(context, "Error:  ${event.throwable}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.nextEvent()
    }

    Column(
        Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .fillMaxSize()
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), onClick = {
            viewModel.getWorldNews()
        }) {
            Text(text = "Load Breaking News")
        }
        Spacer(modifier = Modifier.weight(1f))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            itemsIndexed(viewState.worldNewsList) { _, item ->
                Text(text = item.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(viewModel = WelcomeScreenViewModel())
}