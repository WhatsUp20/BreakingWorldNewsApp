package com.example.breakingworldnewsapp.presentation.ui.detail

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
internal fun DetailScreen(title: String, fullDesc: String, link: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title)
        Text(text = fullDesc)
        OpenURL(link = link)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun OpenURL(link: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(link)
            settings.javaScriptEnabled = true
        }
    })
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(title = "Тестовый заголовок", fullDesc = "Тестовое описание", link = "")
}