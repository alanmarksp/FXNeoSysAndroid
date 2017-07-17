package com.alanmarksp.fxneosys.presenters

import com.alanmarksp.fxneosys.models.Quote
import com.alanmarksp.fxneosys.repositories.QuoteRepository
import io.reactivex.Observable

class ShowQuotesPresenter {
    private var quoteRepository: QuoteRepository? = null

    fun setQuoteRepository(quoteRepository: QuoteRepository) {
        this.quoteRepository = quoteRepository
    }

    fun getQuotes(): Observable<List<Quote>>? {
        return quoteRepository?.getQuotes()
    }
}
