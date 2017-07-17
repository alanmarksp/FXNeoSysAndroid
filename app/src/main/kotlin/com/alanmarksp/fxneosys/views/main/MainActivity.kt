package com.alanmarksp.fxneosys.views.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.utils.Constants
import com.alanmarksp.fxneosys.views.Router
import com.alanmarksp.fxneosys.views.authentication.AuthenticationActivity

class MainActivity : AppCompatActivity(), Router {

    private val routes: HashMap<String, Fragment> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActivity()
    }

    private fun initActivity() {
        val dashboardFragment: Fragment = DashboardFragment.newInstance(this)
        routes[Constants.ROUTES.DASHBOARD] = dashboardFragment
        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_fragment_container, dashboardFragment)
                .commit()
    }

    override fun navigate(route: String) {
        if (route == Constants.ROUTES.AUTHENTICATION) {
            val intent: Intent = Intent(applicationContext, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        } else if (routes.containsKey(route)) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, routes[route])
                    .commit()
        }
    }
}
