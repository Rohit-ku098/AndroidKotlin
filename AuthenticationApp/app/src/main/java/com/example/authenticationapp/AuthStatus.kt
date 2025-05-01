package com.example.authenticationapp

sealed class AuthStatus {
    object Authenticated : AuthStatus()
    object Unauthenticated : AuthStatus()
    object Loading : AuthStatus()
    data class Error(val message: String) : AuthStatus()
}