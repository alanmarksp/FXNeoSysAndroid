package com.alanmarksp.fxneosys.retrofit.repositories

import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.repositories.TraderRepository
import com.alanmarksp.fxneosys.retrofit.RetrofitSingleton
import com.alanmarksp.fxneosys.retrofit.services.TraderService
import io.reactivex.Observable

class TraderRepository : TraderRepository {

    private val traderService: TraderService = RetrofitSingleton
            .retrofit
            .create(TraderService::class.java)

    override fun getProfile(): Observable<Trader> {
        return traderService.getProfile()
    }

}
