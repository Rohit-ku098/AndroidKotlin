package com.example.authenticationapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.authenticationapp.Screens.HomePage
import com.example.authenticationapp.Screens.LoginPage
import com.example.authenticationapp.Screens.SignupPage

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController, startDestination = Routes.LoginPage
    ) {
        composable<Routes.LoginPage> {
            LoginPage(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<Routes.SignupPage> {
            SignupPage(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<Routes.HomePage> {
            HomePage(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}