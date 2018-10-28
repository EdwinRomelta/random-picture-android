package com.edwin.randompicture.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    companion object {
        private const val HEADER_AUTHORIZATION = "authorization"
    }

    private var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request.Builder

        newRequest = request.newBuilder()
        if (token != null) {
            newRequest.addHeader(HEADER_AUTHORIZATION, token)
        }
        return chain.proceed(newRequest.build())
    }

    fun addToken(token: String) {
        this.token = token
    }

    fun clearHeader() {
        token = null
    }
}