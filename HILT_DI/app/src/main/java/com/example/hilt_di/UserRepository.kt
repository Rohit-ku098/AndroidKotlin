package com.example.hilt_di

import android.util.Log
import javax.inject.Inject

interface UserRepository {
    fun saveUser()
}

class RoomUserRepository @Inject constructor(): UserRepository {
    override fun saveUser() {
        Log.d("Hilt_Di_Tag","User saved in Room Database")
    }
}

class FirebaseUserRepository @Inject constructor(): UserRepository {
    override fun saveUser() {
        Log.d("Hilt_Di_Tag","User saved in Firebase")
    }
}