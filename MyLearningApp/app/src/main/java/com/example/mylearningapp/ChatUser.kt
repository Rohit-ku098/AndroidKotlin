package com.example.mylearningapp

import java.io.Serializable

data class ChatUser(
    val name: String,
    val mobile: String,
    val image: Int,
    val message: String,
    val msgTime: String
): Serializable