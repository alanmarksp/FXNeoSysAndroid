package com.alanmarksp.fxneosys.retrofit.services

import com.alanmarksp.fxneosys.models.Quote
import io.reactivex.Observable
import retrofit2.http.GET

interface QuoteService {

    @GET("quotes/")
    fun getQuotes(): Observable<List<Quote>>
}