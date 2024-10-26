package com.example.quoteapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // View Mode Factory
        val mainViewModelFactory = MainViewModelFactory(application)

        // View Model
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        setQuotes()

        binding.prevBtn.setOnClickListener {
            prevQuote()
        }

        binding.nextBtn.setOnClickListener {
            nextQuote()
        }

        binding.shareButton.setOnClickListener {
            onShare()
        }

    }

    fun setQuotes() {
        val quote = mainViewModel.getQuote()
        binding.quoteText.text = quote.quote
        binding.authorName.text = quote.author
    }

    fun nextQuote() {
        mainViewModel.nextQuote()
        setQuotes()
    }

    fun prevQuote() {
        mainViewModel.previousQuote()
        setQuotes()
    }

    fun onShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quote")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().quote)
        startActivity(intent)
    }
}