package com.example.koin_di.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val test: ViewModelTest):ViewModel() {

    private val _count = MutableLiveData<Int>(0)
    val count : LiveData<Int> = _count
    fun increment() {
        _count.value = _count.value?.plus(1)
    }
    fun getTest() {
        test.getTest()
    }


}