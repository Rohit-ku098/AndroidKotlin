package com.example.authenticationapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authenticationapp.AuthStatus
import com.example.authenticationapp.AuthViewModel
import com.example.authenticationapp.Routes

@Composable
fun HomePage(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {

    val authStatus = authViewModel.authStatus.collectAsState()

    LaunchedEffect(authStatus.value) {
        when(authStatus.value) {
            is AuthStatus.Unauthenticated -> navController.navigate(Routes.LoginPage)
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Page", fontSize = 24.sp)

        Button(
            onClick = {
                authViewModel.signout()
            }
        ) {
            Text("Sign Out")
        }
    }
}