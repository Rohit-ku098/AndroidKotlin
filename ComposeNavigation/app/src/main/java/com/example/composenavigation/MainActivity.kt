package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        App()
                    }
                }
            }
        }
    }
}
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        // Node in Nav Graph
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
            val email = it.arguments?.getString("email")
            MainScreen(navController, email)
        }

        // Node in Nav Graph
        composable(route="registration") {
            Registration() {
                navController.navigate(route = "login")
            }
        }

        // Node in Nav Graph
        composable(route = "login") {
            Login(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController, email: String?) {
    Column {
        Text(text="Main Screen\nHello $email", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = {
            navController.navigate(route = "registration")
        }) {
            Text(text = "Go to Registration")
        }

        Button(onClick = {
            navController.navigate(route = "login")
        }) {
            Text(text = "Go to Login")
        }
    }

}

@Composable
fun Registration(onClick: () -> Unit) {
    Column {
        Text(text="Registration Screen", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = {
            onClick()
        }) {
            Text(text = "Go to Login")
        }
    }

}

@Composable
fun Login(navController: NavController) {
    Column {
        Text(text="Login Screen", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = {
            val email = "rohitkr.engineer@gmail.com"
            navController.navigate(route = "main/$email")
        }) {
            Text(text = "Go to Main")
        }
    }
}