package com.alanmarksp.fxneosys.retrofit.repositories

import com.alanmarksp.fxneosys.models.Quote
import com.alanmarksp.fxneosys.repositories.QuoteRepository
import com.alanmarksp.fxneosys.retrofit.RetrofitSingleton
import com.alanmarksp.fxneosys.retrofit.services.QuoteService
import io.reactivex.Observable

class QuoteRepository: QuoteRepository {

    private val quoteService: QuoteService = RetrofitSingleton
            .retrofit
            .create(QuoteService::class.java)

    override fun getQuotes(): Observable<List<Quote>> {
        return quoteService.getQuotes()
    }
}