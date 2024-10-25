package com.example.viewmodelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


// Factory is created for view model to pass the value to the view model as constructor
class MainViewModelFactory(val counter: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(counter) as T
    }
}