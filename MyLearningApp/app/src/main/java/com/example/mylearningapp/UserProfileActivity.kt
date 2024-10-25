package com.example.mylearningapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningapp.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatUser = intent.getSerializableExtra("user") as ChatUser?
        chatUser?.image?.let { binding.profile.setImageResource(it) ?: R.drawable.profile}
        chatUser?.name?.let { binding.name.text = it }
        chatUser?.mobile?.let { binding.mobile.text = it }
    }
}