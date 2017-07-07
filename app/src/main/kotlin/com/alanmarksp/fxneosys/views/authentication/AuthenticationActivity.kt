package com.alanmarksp.fxneosys.views.authentication

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class AuthenticationActivity : AppCompatActivity(), Router {

    private val routes: HashMap<String, Fragment> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val loginFragment: Fragment = LoginFragment.newInstance(this)
        val registerFragment: Fragment = RegisterFragment.newInstance(this)

        routes["login"] = loginFragment
        routes["register"] = registerFragment

        supportFragmentManager
                .beginTransaction()
                .add(R.id.authentication_fragment_container, loginFragment)
                .commit()
    }

    override fun navigate(route: String) {
        if (routes.containsKey(route)) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.authentication_fragment_container, routes[route])
                    .commit()
        }
    }
}
