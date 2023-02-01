package com.yashkasera.musicwiki

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yashkasera.musicwiki.data.Repository
import com.yashkasera.musicwiki.service.CommonNetworkService
import com.yashkasera.musicwiki.util.interceptors.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Constructor
import java.text.DateFormat

class AppObjectController {
    companion object {
        @JvmStatic
        lateinit var musicWikiApplication: MusicWikiApplication

        @JvmStatic
        val gsonMapper: Gson by lazy {
            GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setPrettyPrinting()
                .setLenient()
                .create()
        }

        @JvmStatic
        lateinit var repository: Repository

        fun init(musicWikiApplication: MusicWikiApplication) {
            this.musicWikiApplication = musicWikiApplication
            val builder = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
            if (BuildConfig.DEBUG) {
                builder
                    .addInterceptor(getOkHttpProfilerInterceptor())
                    .addInterceptor(getHttpLoggingInterceptor())
            }
            Log.d("AppObjectController.kt", "YASH => init:44 ${BuildConfig.BASE_URL}")
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            repository = Repository(retrofit.create(CommonNetworkService::class.java))
        }
    }
}

fun getOkHttpProfilerInterceptor(): Interceptor {
    val clazz = Class.forName("com.localebro.okhttpprofiler.OkHttpProfilerInterceptor")
    val ctor: Constructor<*> = clazz.getConstructor()
    return ctor.newInstance() as Interceptor
}

fun getHttpLoggingInterceptor(): Interceptor {
    val clazz = Class.forName("okhttp3.logging.HttpLoggingInterceptor")
    val ctor: Constructor<*> = clazz.getConstructor()
    return ctor.newInstance() as Interceptor
}


