package com.greedygame.testapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greedygame.testapp.data.response.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews() : Flow<List<Article>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNews(id: Int): Flow<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: Article)

    @Query("DELETE FROM news")
    fun deleteNews()
}