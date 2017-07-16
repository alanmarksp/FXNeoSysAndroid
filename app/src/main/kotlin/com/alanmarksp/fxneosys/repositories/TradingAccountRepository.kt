package com.alanmarksp.fxneosys.repositories

import com.alanmarksp.fxneosys.models.TradingAccount
import io.reactivex.Observable

interface TradingAccountRepository {
    fun getTradingAccounts(): Observable<List<TradingAccount>>
}