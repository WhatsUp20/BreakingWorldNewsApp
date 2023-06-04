package com.example.breakingworldnewsapp.presentation.ui.commonViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopicsItem(
    name: String
) {
    Box(
        Modifier
            .padding(top = 14.dp, end = 8.dp)
            .size(width = 132.dp, height = 32.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}