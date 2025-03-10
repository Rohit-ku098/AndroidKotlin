package com.example.mylearningapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningapp.databinding.ActivityChatListViewBinding

class ChatListView : AppCompatActivity() {
    lateinit var chatUser: ArrayList<ChatUser>
    lateinit var binding: ActivityChatListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChatListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        chatUser = ArrayList<ChatUser>()
        chatUser.add(ChatUser("Rohit","9876543210", R.drawable.profile_rohit, "Hi there, I am interested in Android Development.", "10:00 AM"))
        chatUser.add(ChatUser("Vinit","9876543210", R.drawable.profile_vinit, "Hi I am Vinit.", "11:00 PM"))
        chatUser.add(ChatUser("Rohan","9876543210", R.drawable.profile, "Hi", "11:00 PM"))
        chatUser.add(ChatUser("Akshay","9876543210", R.drawable.profile_akshay, "What's up?", "10:00 AM"))
        chatUser.add(ChatUser("Katrina","9876543210", R.drawable.profile_katrina, "Heyy!", "11:00 PM"))
        chatUser.add(ChatUser("Tripti","9876543210", R.drawable.profile_tripti, "Hello.", "11:00 PM"))
        chatUser.add(ChatUser("Hritik","9876543210", R.drawable.profile_hritik, "Hi there, I am interested in Android Development.", "10:00 AM"))
        chatUser.add(ChatUser("Shahrukh","9876543210", R.drawable.profile_shahrukh, "Hi I am Shahrukh.", "11:00 PM"))

        val adapter = MyChatAdapter(this, chatUser)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra("user", chatUser[position])
            startActivity(intent)
        }

    }


}