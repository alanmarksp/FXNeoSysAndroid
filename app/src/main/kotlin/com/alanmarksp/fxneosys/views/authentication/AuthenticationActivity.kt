package com.alanmarksp.fxneosys.views.authentication

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.utils.Constants.ROUTES
import com.alanmarksp.fxneosys.views.Router
import com.alanmarksp.fxneosys.views.main.MainActivity

class AuthenticationActivity : AppCompatActivity(), Router {

    private val routes: HashMap<String, Fragment> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        initActivity()
    }

    private fun initActivity() {
        val loginFragment: Fragment = LoginFragment.newInstance(this)
        val registerFragment: Fragment = RegisterFragment.newInstance(this)
        routes[ROUTES.LOGIN] = loginFragment
        routes[ROUTES.REGISTER] = registerFragment
        supportFragmentManager
                .beginTransaction()
                .add(R.id.authentication_fragment_container, loginFragment)
                .commit()
    }

    override fun navigate(route: String) {
        if (route == ROUTES.MAIN) {
            val intent: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else if (routes.containsKey(route)) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.authentication_fragment_container, routes[route])
                    .commit()
        }
    }
}
