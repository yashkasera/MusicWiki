package com.yashkasera.musicwiki.util

sealed class Result<T> {
    open class Success<T>(val data: T) : Result<T>()
    open class Error<T>(val exception: Exception) : Result<T>() {
        constructor(data: T) : this(Exception(data.toString()))
        constructor(code: Int, message: String) : this(Exception("$code: $message"))
        constructor(message: String) : this(Exception(message))
    }

    open class Loading<T> : Result<T>()
}
