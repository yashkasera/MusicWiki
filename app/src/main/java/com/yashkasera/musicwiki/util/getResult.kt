package com.yashkasera.musicwiki.util

import retrofit2.Response

suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful && response.body() != null)
            return Result.Success(response.body()!!)
        return Result.Error(Exception(response.message()))
    } catch (e: Exception) {
        return Result.Error(e)
    }
}