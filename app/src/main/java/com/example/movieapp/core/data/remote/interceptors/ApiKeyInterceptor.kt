package com.example.movieapp.core.data.remote.interceptors

import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}