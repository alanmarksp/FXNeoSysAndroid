package com.alanmarksp.fxneosys.views.authentication

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.alanmarksp.fxneosys.R

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val loginFragment: Fragment = LoginFragment.newInstance()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.authentication_fragment_container, loginFragment)
                .commit()
    }
}
