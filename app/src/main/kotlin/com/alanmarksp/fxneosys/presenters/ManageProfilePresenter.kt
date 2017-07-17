package com.alanmarksp.fxneosys.presenters

import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.repositories.TraderRepository
import io.reactivex.Observable

class ManageProfilePresenter {
    private var traderRepository: TraderRepository? = null

    fun setTraderRepository(traderRepository: TraderRepository) {
        this.traderRepository = traderRepository
    }

    fun getProfile(): Observable<Trader>? {
        return traderRepository?.getProfile()
    }
}
