package com.example.app.core.result

import com.example.app.core.exception.CommonException
import com.example.app.core.model.response.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * 转为Result
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> = map {
    if (it is NetworkResponse<*>) {
        if (it.isSucceeded) {
            Result.success(it)
        } else {
            Result.failure(CommonException(it))
        }
    } else {
        Result.success(it)
    }
}.onStart {
    //    emit(Result.loading())
}.catch {
    emit(Result.failure(it))
}