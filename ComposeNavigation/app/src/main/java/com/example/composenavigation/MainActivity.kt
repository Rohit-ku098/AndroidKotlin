package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                var bottomNavSelectedIndex by rememberSaveable { mutableStateOf(0) }
                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            navItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (item.badgeCount != null) {
                                                    Badge {
                                                        Text(item.badgeCount.toString())
                                                    }
                                                }
                                                else if(item.badgeFlag) {
                                                    Badge()
                                                }
                                            }
                                        ) {
                                            if(index == bottomNavSelectedIndex)
                                                Icon(item.selectedIcon, contentDescription = item.title)
                                            else
                                                Icon(item.unselectedIcon, contentDescription = item.title)
                                        }
                                    },
                                    selected = index == bottomNavSelectedIndex,
                                    onClick = {
                                        bottomNavSelectedIndex = index
                                    },
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding),
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