package com.greedygame.testapp.ui.save_to_read

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greedygame.testapp.R
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.components.NewsItem
import com.greedygame.testapp.ui.components.SaveToReadItem
import com.greedygame.testapp.ui.news_dashboard.NewsPageViewModel
import com.greedygame.testapp.ui.theme.BgColor

@Composable
fun SaveToReadPage(articles: List<Article>, context: Context) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Surface(
        color = BgColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(articles.size) { index ->
                SaveToReadItem(data = articles[index], context)
            }
        }

    }

}