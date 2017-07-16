package com.alanmarksp.fxneosys.retrofit.repositories

import com.alanmarksp.fxneosys.models.TradingAccount
import com.alanmarksp.fxneosys.repositories.TradingAccountRepository
import com.alanmarksp.fxneosys.retrofit.RetrofitSingleton
import com.alanmarksp.fxneosys.retrofit.services.TradingAccountService
import io.reactivex.Observable

class TradingAccountRepository(): TradingAccountRepository {

    private val tradingAccountsService: TradingAccountService = RetrofitSingleton
            .retrofit
            .create(TradingAccountService::class.java)

    override fun getTradingAccounts(): Observable<List<TradingAccount>> {
        return tradingAccountsService.getTradingAccounts()
    }
}