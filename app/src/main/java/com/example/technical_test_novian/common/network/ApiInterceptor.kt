package com.example.technical_test_novian.common.network

import android.util.Log
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

private const val contentType = "Content-Type"
private const val contentTypeValue = "text/html; charset=UTF-8"

class ApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header(contentType, contentTypeValue)
            .build()

        return chain.proceed(newRequest)
    }
}