package com.alanmarksp.fxneosys.presenters

import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.repositories.AuthenticationRepository
import com.alanmarksp.fxneosys.repositories.TokenRepository
import io.reactivex.Observable

class AuthenticatePresenter(private val authenticationRepository: AuthenticationRepository, private val tokenRepository: TokenRepository) {
    fun login(authentication: Authentication): Observable<String> {
        return authenticationRepository.login(authentication).map {
            tokenRepository.create(it["token"]!!)
            it["token"]!!
        }
    }

    fun register(authentication: Authentication): Observable<String> {
        return authenticationRepository.register(authentication).map {
            tokenRepository.create(it["token"]!!)
            it["token"]!!
        }
    }
}
