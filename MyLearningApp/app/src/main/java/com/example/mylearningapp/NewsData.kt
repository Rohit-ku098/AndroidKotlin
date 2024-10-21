package com.example.mylearningapp

import java.io.Serializable

data class NewsData(
    val heading: String,
    val description: String,
    val image: Int
): Serializable
