package com.alanmarksp.fxneosys.presenters

import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.repositories.AuthenticationRepository
import io.reactivex.Observable

class AuthenticatePresenter(private val authenticationRepository: AuthenticationRepository) {
    fun login(authentication: Authentication): Observable<String> {
        return authenticationRepository.login(authentication).map { it["token"]!! }
    }

    fun register(authentication: Authentication): Observable<String> {
        return authenticationRepository.register(authentication).map { it["token"]!! }
    }
}
