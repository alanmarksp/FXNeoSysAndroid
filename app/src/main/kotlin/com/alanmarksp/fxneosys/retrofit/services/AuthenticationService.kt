package com.alanmarksp.fxneosys.retrofit.services

import com.alanmarksp.fxneosys.models.Authentication
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("authentications/login/")
    fun login(@Body authentication: Authentication): Observable<Map<String, String>>

    @POST("authentications/register/")
    fun register(@Body authentication: Authentication): Observable<Map<String, String>>
}