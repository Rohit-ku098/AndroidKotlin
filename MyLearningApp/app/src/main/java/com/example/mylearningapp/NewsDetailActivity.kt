package com.example.mylearningapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningapp.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = intent.getSerializableExtra("news") as NewsData
        binding.headingText.text = news.heading
        binding.newsHeadingImage.setImageResource(news.image)
        binding.newsDescription.text = news.description

    }
}