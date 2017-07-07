package com.alanmarksp.fxneosys.retrofit

import com.alanmarksp.fxneosys.interceptors.TokenInterceptor
import com.alanmarksp.fxneosys.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitSingleton {

    companion object {
        private var client = OkHttpClient.Builder().build()
        var retrofit = buildRetrofit()

        fun addToken(token: String) {
            val tokenInterceptor = TokenInterceptor(token)
            client = OkHttpClient
                    .Builder()
                    .addInterceptor(tokenInterceptor)
                    .build()
            retrofit = buildRetrofit()
        }

        fun removeToken() {
            OkHttpClient.Builder().build()
            retrofit = buildRetrofit()
        }

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.REST_API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        }
    }
}