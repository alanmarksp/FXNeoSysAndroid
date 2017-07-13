package com.alanmarksp.fxneosys.retrofit.services

import com.alanmarksp.fxneosys.models.Trader
import io.reactivex.Observable
import retrofit2.http.GET

interface TraderService {

    @GET("traders/profile/")
    fun getProfile(): Observable<Trader>
}