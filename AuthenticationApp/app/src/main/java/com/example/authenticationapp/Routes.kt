package com.example.authenticationapp

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object LoginPage

    @Serializable
    object SignupPage

    @Serializable
    object HomePage
}