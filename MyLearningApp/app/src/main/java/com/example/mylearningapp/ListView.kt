package com.example.mylearningapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylearningapp.databinding.ActivityListViewBinding

class ListView : AppCompatActivity() {
    lateinit var binding: ActivityListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvBtn1.setOnClickListener {
            startActivity(Intent(this, TodoListView::class.java))
        }

        binding.lvBtn2.setOnClickListener {
            startActivity(Intent(this, ChatListView::class.java))
        }
    }
}