package com.example.authenticationapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel: ViewModel() {
    private val _authStatus = MutableStateFlow<AuthStatus>(AuthStatus.Loading)
    val authStatus: StateFlow<AuthStatus> = _authStatus

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        try {
            if(auth.currentUser != null) {
                Log.d("AuthenticationTag", "checkAuthStatus: \nEmail:${auth.currentUser?.email}" +
                        "\nUid:${auth.currentUser?.uid}" +
                        "\nDisplayName:${auth.currentUser?.displayName}")
                _authStatus.value = AuthStatus.Authenticated
            } else {
                _authStatus.value = AuthStatus.Unauthenticated
            }
        } catch (e: Exception) {
            _authStatus.value = AuthStatus.Error(e.message ?: "Something Went Wrong")
            Log.d("AuthenticationTag", "CheckAuthStatus Exception: ${e.message}")
        }
    }

     fun login(email: String, password: String) {
         viewModelScope.launch {
             if(email.isEmpty() || password.isEmpty()) {
                 _authStatus.value = AuthStatus.Error("Email and password cannot be empty")
                 return@launch
             }

             try {
                 val response = auth.signInWithEmailAndPassword(email, password).await()

                 Log.d("AuthenticationTag", "Login Data: Email: ${response.user?.email} \ncredential:${response.credential} \nadditionalUserInfo ${response.additionalUserInfo} \nUid: ${response.user?.uid} ")
                 if(response.user?.uid != null) {
                     _authStatus.value = AuthStatus.Authenticated
                 } else {
                     _authStatus.value = AuthStatus.Error("Something Went Wrong")
                 }
             } catch (e: Exception) {
                 Log.d("AuthenticationTag", "Login Exception: ${e.message}")
             }
         }


    }

    fun signup(email: String, password: String, username: String) {
        val auth = FirebaseAuth.getInstance()

        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            user?.let {
                                // Update display name in Firebase Authentication
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build()
                                it.updateProfile(profileUpdates)

                            }
                        } else {
                            Log.e("Auth", "Sign-up failed", task.exception)
                        }
                    }.await()
            } catch (e: Exception) {
                Log.d("AuthenticationTag", "SignUp Exception: ${e.message}")
            }
        }

    }


    fun signout() {
        auth.signOut()
        _authStatus.value = AuthStatus.Unauthenticated
    }
}