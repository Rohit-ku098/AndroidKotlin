package com.example.mylearningapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningapp.databinding.ActivityPhotoFrameBinding

class PhotoFrame : AppCompatActivity() {
    private lateinit var binding : ActivityPhotoFrameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentImage = 0
        val names = arrayOf("India Gate", "Taj Mahal", "Eiffel Tower", "Lotus Temple")
        val images = arrayOf(R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3)


        binding.prevBtn.setOnClickListener {
            currentImage = (currentImage - 1 + images.size) % images.size
            binding.frameImg.setImageResource(images[currentImage])
            binding.frameTxt.text = names[currentImage]
        }

        binding.nextBtn.setOnClickListener {
            currentImage = (currentImage + 1) % images.size
            binding.frameImg.setImageResource(images[currentImage])
            binding.frameTxt.text = names[currentImage]
        }
    }
}