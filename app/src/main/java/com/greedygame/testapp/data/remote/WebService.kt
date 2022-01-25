package com.greedygame.testapp.data.remote

import com.greedygame.testapp.data.response.NewsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WebService {
    @GET("v2/everything")
    suspend fun getAllNews(@QueryMap request:Map<String,String>) : Response<NewsResponse>
}