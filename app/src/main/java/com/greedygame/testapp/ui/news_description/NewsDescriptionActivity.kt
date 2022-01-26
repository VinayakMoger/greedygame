package com.greedygame.testapp.ui.news_description

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.greedygame.testapp.ui.theme.NEWS_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDescriptionActivity: ComponentActivity() {
    private val viewModel: NewsDescriptionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(NEWS_KEY,-1)
        if(id!=-1) {
            viewModel.getNewsItem(id)
            setContent {
                DescriptionCompose(viewModel)
            }
        }
    }
}