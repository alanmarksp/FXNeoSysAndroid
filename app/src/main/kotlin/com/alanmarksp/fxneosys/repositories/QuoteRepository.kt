package com.alanmarksp.fxneosys.repositories

import com.alanmarksp.fxneosys.models.Quote
import io.reactivex.Observable

interface QuoteRepository {
    fun getQuotes(): Observable<List<Quote>>
}