package com.edwin.randompicture.remote.interceptor

import com.edwin.randompicture.remote.exception.RemoteLayerException
import com.edwin.randompicture.remote.model.ErrorModel
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class ResponseCodeInterceptor(private val gson: Gson) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val responseCode = response.code()

        when (responseCode) {
            in 200..299 -> {
                return response
            }
            in 400..499 -> {
                response.body()?.string()?.let {
                    val errorModel = gson.fromJson(it, ErrorModel::class.java)
                    throw RemoteLayerException(errorModel.message)
                }

            }
        }
        throw RemoteLayerException("Unhandled Response Code $responseCode")
    }
}