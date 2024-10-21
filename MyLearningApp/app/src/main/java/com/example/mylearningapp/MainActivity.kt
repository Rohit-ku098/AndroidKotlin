package com.example.mylearningapp

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mylearningapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dialogboxBtn.setOnClickListener {
            val intent = Intent(this, Dialogbox::class.java)
            startActivity(intent)
        }

        binding.photoframeBtn.setOnClickListener {
            val intent = Intent(this, PhotoFrame::class.java)
            startActivity(intent)
        }

        binding.listViewBtn.setOnClickListener {
            startActivity(Intent(this, ListView::class.java))
        }

        binding.recyclerViewBtn.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
    }
}