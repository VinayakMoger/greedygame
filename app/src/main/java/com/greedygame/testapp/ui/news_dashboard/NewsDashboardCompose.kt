package com.greedygame.testapp.ui.news_dashboard

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.greedygame.testapp.ui.components.NewsItem
import com.greedygame.testapp.ui.theme.BgColor

@Composable
fun DashBoardPage(viewModel:NewsPageViewModel,context: Context) {
    if(viewModel.isSuccess.collectAsState().value) {
        Surface(
            color = BgColor,
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(viewModel.newsResponse.size) { index ->
                    NewsItem(data = viewModel.newsResponse[index],context)
                }
            }
        }
    }
}