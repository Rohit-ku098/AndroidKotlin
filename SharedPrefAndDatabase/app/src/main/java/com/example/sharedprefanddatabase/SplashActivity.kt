package com.example.sharedprefanddatabase

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pref = getSharedPreferences("login", MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = pref.getBoolean("isLogin", false)

            val intent =
                if(isLogin)
                    Intent(applicationContext, MainActivity::class.java)
                else
                    Intent(applicationContext, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }, 2000)

    }
}