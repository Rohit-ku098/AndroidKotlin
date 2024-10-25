package com.example.mylearningapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mylearningapp.databinding.ActivityRecyclerViewNewsBinding

class RecyclerViewNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewNewsBinding
    lateinit var newsDataList: ArrayList<NewsData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsDataList= ArrayList<NewsData>()
        newsDataList.add(
            NewsData(
                "How To Transfer Data From Android To iPhone Easily",
                "You have to follow the steps that I am going to share when you first turn on your iPhone and set it up. If you have already set up your iPhone, you will have to either reset the iPhone or manually transfer the data.\n" +
                        "Step 1: Turn on your new iPhone and you will see the ‘Hello’ screen. Place it near your Android phone.\n" +
                        "\n" +
                        "Step 2: Follow all the instructions on your iPhone until you reach the “Quick Start” screen. Here click on “Set Up Without Another Device”.\n" +
                        "\n" +
                        "Step 3: Then go to the “Transfer Your Apps & Data” screen and select “From Android” from the given menu. ",
                R.drawable.news1
            )
        )

        newsDataList.add(
            NewsData(
                "Apple announces Swift Student Challenge 2025: Dates, participation details and more",
                "pple has announced the 2025 edition of the Swift Student Challenge. The company will start accepting submissions from February 2025, offering students from across the globe the opportunity to showcase their creativity and develop real-world skills. The challenge encourages students to join a global community of developers using Swift, the programming language used by professionals, to build innovative apps. In the past years, a few student developers from India have also won the Swift Student Challenge.",
                R.drawable.news2
            )
        )

        newsDataList.add(
            NewsData(
                "Spotify starts free streaming option in South Korea",
                "Spotify’s free music and podcasts service option is now available in South Korea, the audio streaming platform said on Thursday, as it looks to widen its audience base.\nSpotify said that the move will enable them to better connect global audience with Korean music, which includes K-pop.\n" +
                        "\n" +
                        "Users in South Korea can now sign-up for a free account to access Spotify’s playlists, podcasts, and daily mix among others and can further choose to upgrade to premium on-demand, ad-free music." +
                        "\n" +
                        "The streaming giant’s freemium business model offers both a free, limited ad-supported service and an unlimited premium subscription service.",
                R.drawable.news3
            )
        )

        newsDataList.add(
            NewsData(
                "How To Quickly Block Your Lost Debit or Credit Card: A Simple Step-by-Step Guide",
                "# Blocking Your Card Online\n" +
                        "\n" +
                        "1. Access Your Bank’s Platform: Log in to your bank’s online banking portal or mobile app.\n" +
                        "\n" +
                        "2. Navigate to Card Details: Locate the section dedicated to your debit or credit card details.\n" +
                        "\n" +
                        "3. Initiate Block Request: Click on the option to block the card and provide a reason for the action.\n" +
                        "\n" +
                        "4. Verify Your Request: Submit your request and follow any additional instructions. You will receive an OTP (One-Time Password) on your registered phone number.\n" +
                        "\n" +
                        "5. Confirm Blocking: Enter the OTP to finalize the card block. You will receive a confirmation SMS once the process is complete.\n" +
                        "\n" +
                        "# Blocking Your Card Offline\n" +
                        "\n" +
                        "1. Visit Your Bank Branch: Go to your nearest bank branch.\n" +
                        "\n" +
                        "2. Request Assistance: Inform the bank officer that you need to block your debit or credit card. They will guide you through the process.",
                R.drawable.news4
            )
        )

        newsDataList.add(
            NewsData(
                "How To Transfer Data From Android To iPhone Easily",
                "You have to follow the steps that I am going to share when you first turn on your iPhone and set it up. If you have already set up your iPhone, you will have to either reset the iPhone or manually transfer the data.\n" +
                        "Step 1: Turn on your new iPhone and you will see the ‘Hello’ screen. Place it near your Android phone.\n" +
                        "\n" +
                        "Step 2: Follow all the instructions on your iPhone until you reach the “Quick Start” screen. Here click on “Set Up Without Another Device”.\n" +
                        "\n" +
                        "Step 3: Then go to the “Transfer Your Apps & Data” screen and select “From Android” from the given menu. ",
                R.drawable.news1
            )
        )

        newsDataList.add(
            NewsData(
                "Apple announces Swift Student Challenge 2025: Dates, participation details and more",
                "pple has announced the 2025 edition of the Swift Student Challenge. The company will start accepting submissions from February 2025, offering students from across the globe the opportunity to showcase their creativity and develop real-world skills. The challenge encourages students to join a global community of developers using Swift, the programming language used by professionals, to build innovative apps. In the past years, a few student developers from India have also won the Swift Student Challenge.",
                R.drawable.news2
            )
        )

        newsDataList.add(
            NewsData(
                "Spotify starts free streaming option in South Korea",
                "Spotify’s free music and podcasts service option is now available in South Korea, the audio streaming platform said on Thursday, as it looks to widen its audience base.\nSpotify said that the move will enable them to better connect global audience with Korean music, which includes K-pop.\n" +
                        "\n" +
                        "Users in South Korea can now sign-up for a free account to access Spotify’s playlists, podcasts, and daily mix among others and can further choose to upgrade to premium on-demand, ad-free music." +
                        "\n" +
                        "The streaming giant’s freemium business model offers both a free, limited ad-supported service and an unlimited premium subscription service.",
                R.drawable.news3
            )
        )

        newsDataList.add(
            NewsData(
                "How To Quickly Block Your Lost Debit or Credit Card: A Simple Step-by-Step Guide",
                "# Blocking Your Card Online\n" +
                        "\n" +
                        "1. Access Your Bank’s Platform: Log in to your bank’s online banking portal or mobile app.\n" +
                        "\n" +
                        "2. Navigate to Card Details: Locate the section dedicated to your debit or credit card details.\n" +
                        "\n" +
                        "3. Initiate Block Request: Click on the option to block the card and provide a reason for the action.\n" +
                        "\n" +
                        "4. Verify Your Request: Submit your request and follow any additional instructions. You will receive an OTP (One-Time Password) on your registered phone number.\n" +
                        "\n" +
                        "5. Confirm Blocking: Enter the OTP to finalize the card block. You will receive a confirmation SMS once the process is complete.\n" +
                        "\n" +
                        "# Blocking Your Card Offline\n" +
                        "\n" +
                        "1. Visit Your Bank Branch: Go to your nearest bank branch.\n" +
                        "\n" +
                        "2. Request Assistance: Inform the bank officer that you need to block your debit or credit card. They will guide you through the process.",
                R.drawable.news4
            )
        )

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this )
        val newsAdapter = MyRecyclerViewNewsAdapter(newsDataList)
        binding.newsRecyclerView.adapter = newsAdapter
        newsAdapter.setOnItemClickListner(object : MyRecyclerViewNewsAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, NewsDetailActivity::class.java)
                intent.putExtra("news", newsDataList[position])
                startActivity(intent)
            }

        })

    }
}