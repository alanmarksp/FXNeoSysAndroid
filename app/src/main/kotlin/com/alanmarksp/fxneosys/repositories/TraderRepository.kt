package com.alanmarksp.fxneosys.repositories

import com.alanmarksp.fxneosys.models.Trader
import io.reactivex.Observable

interface TraderRepository {
    fun getProfile(): Observable<Trader>
}
