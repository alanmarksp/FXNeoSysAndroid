package com.alanmarksp.fxneosys.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent = Intent(applicationContext, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
