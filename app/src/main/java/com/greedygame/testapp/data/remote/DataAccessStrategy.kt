package com.greedygame.testapp.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

fun <T,A> performGetOperation(databaseQuery: () -> Flow<T>,networkCall: suspend () -> Resource<A>,  saveCallResult: suspend (A) -> Unit): Flow<Resource<T>> =
    flow{
        emit(Resource.loading())
        val source = databaseQuery.invoke().map {
            Resource.success(it)
        }
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            emitAll(source)
        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(message = responseStatus.message!!, data = null))
            emitAll(source)
        }

      //  emitAll(source)
    }.flowOn(Dispatchers.IO)