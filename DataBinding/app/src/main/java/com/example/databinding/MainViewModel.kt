package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var quote = MutableLiveData<String>("The greatest glory in living lies not in never falling, but in rising every time we fall.")

    fun updateQuote() {
        quote.value = "Updated: The greatest glory in living lies not in never falling, but in rising every time we fall."
    }
}