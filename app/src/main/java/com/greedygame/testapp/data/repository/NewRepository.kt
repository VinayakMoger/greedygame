package com.greedygame.testapp.data.repository

import com.greedygame.testapp.data.local.NewsDao
import com.greedygame.testapp.data.remote.RemoteDataSource
import com.greedygame.testapp.data.remote.Resource
import com.greedygame.testapp.data.remote.performGetOperation
import com.greedygame.testapp.data.response.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Respository class for News Module
 */
class NewRepository  @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: NewsDao
) {

    fun getNews(requestBody: Map<String,String>): Flow<Resource<List<Article>>> {
        return performGetOperation(
            databaseQuery = { localDataSource.getAllNews() },
            networkCall = { remoteDataSource.getAllNews(requestBody) },
            saveCallResult = {
                localDataSource.deleteNews()
                localDataSource.insertAll(it.articles)
            }
        )
    }
    fun getNewsItem(id:Int) = localDataSource.getNews(id)
}