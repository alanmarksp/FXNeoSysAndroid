package com.alanmarksp.fxneosys.utils

class Constants {
    companion object {
        val REST_API_URL = "http://192.168.1.41:8000/api/1.0/"

        val AUTHORIZATION_HEADER_KEY = "Authorization"
        val AUTHORIZATION_HEADER_VALUE_PREFIX = "Token"

        val SHARED_PREFERENCES_TOKEN_KEY = "Token"
    }

    class ROUTES {
        companion object {
            val AUTHENTICATION = "authentication"
            val LOGIN = "login"
            val REGISTER = "register"
            val MAIN = "main"
            val DASHBOARD = "dashboard"
        }
    }
}