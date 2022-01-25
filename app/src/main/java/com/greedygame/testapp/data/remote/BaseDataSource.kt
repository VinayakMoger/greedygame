package com.greedygame.testapp.data.remote

import org.json.JSONObject
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) Resource.success(body)
                else {
                    Resource.success(null)
                }
            }
            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
            if(jsonObj.has("message")) {
                return error(jsonObj.getString("message"))
            }

            return error("Something went wrong")
        } catch (e: Exception) {
            return error(e.message!!)
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message =message,data = null)
    }


}