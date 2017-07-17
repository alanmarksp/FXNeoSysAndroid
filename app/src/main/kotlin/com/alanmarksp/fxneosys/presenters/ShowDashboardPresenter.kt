package com.alanmarksp.fxneosys.presenters

import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.models.TradingAccount
import com.alanmarksp.fxneosys.repositories.TraderRepository
import com.alanmarksp.fxneosys.repositories.TradingAccountRepository
import io.reactivex.Observable

class ShowDashboardPresenter {
    private var traderRepository: TraderRepository? = null
    private var tradingAccountRepository: TradingAccountRepository? = null

    fun setTraderRepository(traderRepository: TraderRepository) {
        this.traderRepository = traderRepository
    }

    fun setTradingAccountRepository(tradingAccountRepository: TradingAccountRepository) {
        this. tradingAccountRepository = tradingAccountRepository
    }

    fun getProfile(): Observable<Trader>? {
        return traderRepository?.getProfile()
    }

    fun getTradingAccounts(): Observable<List<TradingAccount>>? {
        return tradingAccountRepository?.getTradingAccounts()
    }

    fun getSelectedTradingAccount(): Observable<TradingAccount>? {
        return tradingAccountRepository?.getSelectedTradingAccount()
    }
}
