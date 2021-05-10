package com.app.demo.di.module

import com.app.demo.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apimodule = module {
      single { createOkHttpClient() }
      single { createRetrofit(get()) }
}

fun createOkHttpClient( ): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    addTimeout(clientBuilder)

    clientBuilder.addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        requestBuilder
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
        chain.proceed(requestBuilder.build())
    }.build()
    clientBuilder.addInterceptor(loggingInterceptor())
    return clientBuilder.build()
}

private fun addTimeout(clientBuilder: OkHttpClient.Builder) {
    clientBuilder
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
}

fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String = BASE_URL): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


fun loggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

