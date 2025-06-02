package com.createfuture.takehome.data.api

import com.createfuture.takehome.utils.Constants.AUTHORIZATION
import com.createfuture.takehome.utils.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(AUTHORIZATION, TOKEN)

        val newRequest = request.build()
        return chain.proceed(newRequest)
    }
}