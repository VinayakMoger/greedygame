package com.greedygame.testapp.data.response

import androidx.room.Entity


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)