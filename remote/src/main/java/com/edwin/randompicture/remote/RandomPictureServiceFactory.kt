package com.edwin.randompicture.remote

import com.edwin.randompicture.remote.interceptor.ResponseCodeInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

object RandomPictureServiceFactory {

    private val gson = GsonBuilder()
            .setLenient()
            .setDateFormat(DATE_FORMAT)
            .create()
    private val logging = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    private val okHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addInterceptor(ResponseCodeInterceptor(gson))
                    .build()

    fun createService(baseUrl: String): RandomPictureService =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(RandomPictureService::class.java)

}