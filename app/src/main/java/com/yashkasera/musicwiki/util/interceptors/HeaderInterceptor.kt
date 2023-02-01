package com.yashkasera.musicwiki.util.interceptors

import com.yashkasera.musicwiki.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("format", "json")
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}