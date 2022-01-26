package com.greedygame.testapp.data.remote

/**
 * Execute abstraction functions of calling Rest API
 */
import javax.inject.Inject
class RemoteDataSource @Inject constructor(
    private val webService: WebService
): BaseDataSource() {

    suspend fun getAllNews(request: Map<String,String>) = getResult { webService.getAllNews(request) }
}