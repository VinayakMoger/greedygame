package com.greedygame.testapp.ui.save_to_read

/**
 * Save to read page design implementation (Compose)
 */
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greedygame.testapp.data.response.Article
import com.greedygame.testapp.ui.components.SaveToReadItem
import com.greedygame.testapp.ui.theme.BgColor

@Composable
fun SaveToReadPage(articles: List<Article>, context: Context) {
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