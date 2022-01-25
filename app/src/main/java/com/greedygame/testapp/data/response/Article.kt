package com.greedygame.testapp.data.response
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)