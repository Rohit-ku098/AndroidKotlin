package com.example.sharedprefanddatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedprefanddatabase.database.MyDatabase
import com.example.sharedprefanddatabase.database.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class LoginActivity : AppCompatActivity() {
    lateinit var database: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = MyDatabase.getInstance(this)

        // To save in data base
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val name = nameInput.text.toString()
            val password = passwordInput.text.toString()

            if(email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()){
                val pref = getSharedPreferences("login", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putBoolean("isLogin",true)
                editor.apply()

                val user = UserEntity(0,name, email, password, Date())
                GlobalScope.launch {
                    database.userDao().insert(user)
                }
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else {
                if(email.isEmpty()){
                    findViewById<EditText>(R.id.emailInput).error = "Please enter email"
                }
                if(name.isEmpty()){
                    findViewById<EditText>(R.id.nameInput).error = "Please enter name"
                }
                if(password.isEmpty()){
                    findViewById<EditText>(R.id.passwordInput).error = "Please enter password"
                }
            }

        }
    }
}