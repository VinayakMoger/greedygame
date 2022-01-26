package com.greedygame.testapp.ui

import android.app.Application
import com.greedygame.testapp.data.response.Article
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val saveToReadList = mutableListOf<Article>()

    fun addItem(article: Article) {
        saveToReadList.add(article)
    }
}