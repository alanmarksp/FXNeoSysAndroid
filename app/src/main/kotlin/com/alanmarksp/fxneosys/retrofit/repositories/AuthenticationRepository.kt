package com.alanmarksp.fxneosys.retrofit.repositories

import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.retrofit.RetrofitSingleton
import com.alanmarksp.fxneosys.retrofit.services.AuthenticationService
import io.reactivex.Observable

class AuthenticationRepository : com.alanmarksp.fxneosys.repositories.AuthenticationRepository {

    private val authenticationService: AuthenticationService = RetrofitSingleton
            .retrofit
            .create(AuthenticationService::class.java)

    override fun login(authentication: Authentication): Observable<Map<String, String>> {
        return authenticationService.login(authentication)
    }

    override fun register(authentication: Authentication): Observable<Map<String, String>> {
        return authenticationService.register(authentication)
    }

}
