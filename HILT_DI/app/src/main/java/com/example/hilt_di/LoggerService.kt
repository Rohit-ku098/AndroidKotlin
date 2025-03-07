package com.example.hilt_di

import android.util.Log
import javax.inject.Inject

class LoggerService @Inject constructor() {
    fun log(message: String) {
        Log.d( "Hilt_Di_Tag",message)
    }
}