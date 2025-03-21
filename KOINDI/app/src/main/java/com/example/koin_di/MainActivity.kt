package com.example.koin_di

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.koin_di.demo.Car
import com.example.koin_di.di.Component
import com.example.koin_di.di.User
import com.example.koin_di.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val component: Component = Component()
    private val viewModel: MainViewModel by viewModel()
    private val user: User by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        component.car.getCar()
        component.testDemoInterface.getTestDemoInterface()
        component.mainViewModel.getTest()

        val counterText = findViewById<TextView>(R.id.counterText)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        viewModel.count.observe(this) {
            counterText.text =  it.toString()
        }

        incrementButton.setOnClickListener {
            viewModel.increment()
        }

        Log.d("Koin-DI-Learning", "User name: ${user.getUser()}")
    }
}