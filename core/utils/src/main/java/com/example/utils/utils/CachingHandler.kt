package com.example.utils.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> cachingHandler(
    crossinline localSource: () -> Flow<ResultType>,
    crossinline remoteSource: suspend () -> RequestType,
    crossinline saveResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {
//    val data = query().firstOrNull()

//    val flow = if (shouldFetch(data)) {
//        emit(data)

        val data = try {
            val remoteData = remoteSource()
            saveResult(remoteData)
            localSource()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            localSource()
        }
//    } else {
//        query().map { it }
//    }

    emitAll(data)
}