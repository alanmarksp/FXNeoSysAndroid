package com.alanmarksp.fxneosys.local.repositories

import android.content.SharedPreferences
import com.alanmarksp.fxneosys.repositories.TokenRepository
import com.alanmarksp.fxneosys.utils.Constants


class TokenRepository(private val sharedPreferences: SharedPreferences) : TokenRepository {
    override fun get(): String? {
        return sharedPreferences.getString(Constants.SHARED_PREFERENCES_TOKEN_KEY, null)
    }

    override fun create(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(Constants.SHARED_PREFERENCES_TOKEN_KEY, token)
        editor.apply()
    }

    override fun destroy() {
        sharedPreferences.all.remove(Constants.SHARED_PREFERENCES_TOKEN_KEY)
    }
}