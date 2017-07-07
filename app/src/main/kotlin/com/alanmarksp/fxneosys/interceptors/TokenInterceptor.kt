package com.alanmarksp.fxneosys.interceptors

import com.alanmarksp.fxneosys.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request
                .newBuilder()
                .header(
                        Constants.AUTHORIZATION_HEADER_KEY,
                        "${Constants.AUTHORIZATION_HEADER_VALUE_PREFIX} $token"
                )
                .build()

        return chain.proceed(newRequest)
    }
}