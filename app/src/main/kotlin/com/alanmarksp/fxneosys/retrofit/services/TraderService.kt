package com.alanmarksp.fxneosys.retrofit.services

import com.alanmarksp.fxneosys.models.Trader
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface TraderService {

    @GET("traders/profile/")
    fun getProfile(): Observable<Trader>

    @PATCH("traders/profile/")
    fun updateProfile(@Body trader: Trader): Observable<Trader>
}