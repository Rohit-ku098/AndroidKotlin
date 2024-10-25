package com.example.viewmodelapp

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var counter2 = 0

    fun increment() {
        counter2++
    }
}