package com.example.koin_di.viewmodel

import android.util.Log

interface ViewModelTest {
    fun getTest()
}

class ViewModelTestImpl : ViewModelTest {
    override fun getTest() {
        Log.d("Koin-DI-Learning", "ViewModel test executed...")
    }
}