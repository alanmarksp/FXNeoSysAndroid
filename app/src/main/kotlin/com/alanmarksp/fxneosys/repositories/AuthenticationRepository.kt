package com.alanmarksp.fxneosys.repositories

import com.alanmarksp.fxneosys.models.Authentication
import io.reactivex.Observable

interface AuthenticationRepository {

    fun login(authentication: Authentication): Observable<Map<String, String>>

    fun register(authentication: Authentication): Observable<Map<String, String>>
}
