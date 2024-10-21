package com.example.mylearningapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningapp.databinding.ActivityTodoListViewBinding

class TodoListView : AppCompatActivity() {
    lateinit var binding: ActivityTodoListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTodoListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val todoArr = arrayListOf<String>()
        todoArr.add("Learn Kotlin")
        todoArr.add("Learn Android")
        todoArr.add("Preparation for interview")
        todoArr.add("Go to market")
        todoArr.add("Go to gym")

        val todoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,todoArr)
        binding.listView.adapter = todoAdapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Item clicked: ${(view as TextView).text.toString()}", Toast.LENGTH_SHORT).show()
        }
    }
}