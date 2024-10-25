package com.example.viewmodelapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    var counter1 = 0
    lateinit var counterTxt1: TextView
    lateinit var counterTxt2: TextView
    lateinit var incrementBtn1: Button
    lateinit var incrementBtn2: Button
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        counterTxt1 = findViewById<TextView>(R.id.counter1)
        counterTxt2 = findViewById<TextView>(R.id.counter2)
        incrementBtn1 = findViewById<Button>(R.id.incrementButton1)
        incrementBtn2 = findViewById<Button>(R.id.incrementButton2)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setText1()
        setText2()

        incrementBtn1.setOnClickListener {
            increment1()
        }
        incrementBtn2.setOnClickListener {
            increment2()
        }
    }
    fun setText1() {
        counterTxt1.text = counter1.toString()
    }

    fun setText2() {
        counterTxt2.text = viewModel.counter2.toString()
    }
    fun increment1() {
        counter1++
        setText1()
    }

    fun increment2() {
        viewModel.increment()
        setText2()
    }
}