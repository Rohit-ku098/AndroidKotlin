package com.example.sharedprefanddatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharedprefanddatabase.database.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: MyDatabase
    lateinit var adapter: UserRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        database = MyDatabase.getInstance(this)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        database.userDao().getAllUser().observe(this, {
            adapter = UserRecyclerAdapter(it)
            recyclerView.adapter = adapter

            adapter.setOnItemClickListener(object : UserRecyclerAdapter.OnItemClickListener {
                override fun onDeleteClick(position: Int) {
                    CoroutineScope(Dispatchers.IO).launch {
                        database.userDao().delete(it[position])
                    }
                    adapter.notifyItemRemoved(position)
                }
            })
        })

        val logoutBtn = findViewById<Button>(R.id.logoutButton)
        logoutBtn.setOnClickListener {
            val pref = getSharedPreferences("login", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("isLogin",false)
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}