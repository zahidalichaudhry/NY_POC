package com.nytimes.poc.model.interceptors

import okhttp3.Headers.Builder
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class BaseHeadersInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val headers = Builder()
        headers.add("Content-Type", "application/json")
        headers.add("Accept", "application/json")
        headers.add("deviceType", "android")
        headers.add("Connection", "close")
        var request = chain.request()
        request = request.newBuilder().headers(headers.build()).build()
        return chain.proceed(request)
    }
}
