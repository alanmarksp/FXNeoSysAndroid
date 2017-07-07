package com.alanmarksp.fxneosys.repositories

interface TokenRepository {

    fun get(): String?

    fun create(token: String)

    fun destroy()
}
