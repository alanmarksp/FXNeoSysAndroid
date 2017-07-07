package com.alanmarksp.fxneosys.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.local.repositories.TokenRepository
import com.alanmarksp.fxneosys.utils.Constants.ROUTES
import com.alanmarksp.fxneosys.views.authentication.AuthenticationActivity
import com.alanmarksp.fxneosys.views.main.MainActivity

class SplashActivity : AppCompatActivity(), Router {

    private val routes: HashMap<String, Class<*>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routes[ROUTES.AUTHENTICATION] = AuthenticationActivity::class.java
        routes[ROUTES.MAIN] = MainActivity::class.java

        val sharePreferences = getSharedPreferences(
                getString(R.string.com_alanmarksp_fxneosys_SHARED_PREFERENCES_KEY),
                Context.MODE_PRIVATE
        )
        val tokenRepository = TokenRepository(sharePreferences)
        if (tokenRepository.get() != null){
            navigate(ROUTES.MAIN)
        }
        else {
            navigate(ROUTES.AUTHENTICATION)
        }
    }

    override fun navigate(route: String) {
        val intent: Intent = Intent(applicationContext, routes[route])
        startActivity(intent)
        finish()
    }
}
