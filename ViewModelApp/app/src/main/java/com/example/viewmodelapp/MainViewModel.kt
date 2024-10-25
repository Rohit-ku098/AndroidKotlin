package com.example.viewmodelapp

import androidx.lifecycle.ViewModel

class MainViewModel(val initialValue: Int): ViewModel() {
    var counter2 = initialValue

    fun increment() {
        counter2++
    }
}