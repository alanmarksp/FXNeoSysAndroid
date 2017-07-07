package com.alanmarksp.fxneosys.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alanmarksp.fxneosys.views.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent = Intent(applicationContext, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
