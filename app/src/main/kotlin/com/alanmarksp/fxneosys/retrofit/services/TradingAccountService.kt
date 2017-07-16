package com.alanmarksp.fxneosys.retrofit.services

import com.alanmarksp.fxneosys.models.TradingAccount
import io.reactivex.Observable
import retrofit2.http.GET

interface TradingAccountService {

    @GET("trading_accounts/")
    fun getTradingAccounts(): Observable<List<TradingAccount>>
}