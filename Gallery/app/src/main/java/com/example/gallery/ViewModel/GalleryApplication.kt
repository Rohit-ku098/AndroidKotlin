package com.example.gallery.ViewModel


import android.app.Application
import android.content.ContentResolver
import com.example.gallery.ViewModel.HomeViewModel
import com.example.gallery.ViewModel.HomeViewModelFactory

class GalleryApplication : Application() {
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate() {
        super.onCreate()
        // Initialize the Singleton HomeViewModel with ContentResolver
        homeViewModel = HomeViewModelFactory(contentResolver).create(HomeViewModel::class.java)
    }
}
