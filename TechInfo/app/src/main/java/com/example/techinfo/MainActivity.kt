package com.example.techinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    companion object {
        val Name = "com.example.techinfo.MainActivity.Name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            val userName = findViewById<EditText>(R.id.userName)
            val name = userName.text.toString().trim()
            if(name == "") {
                userName.setError("Please Enter Your Name")
            }
            else {
                intent = Intent(applicationContext, Signup::class.java)
                intent.putExtra(Name, name)
                startActivity(intent)
            }
        }
    }
}