package com.example.composenavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    var badgeFlag: Boolean,
    var badgeCount: Int? = null
)

val navItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        badgeFlag = false,
    ),
    BottomNavItem(
        title = "Chat",
        route = "chat",
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        badgeFlag = false,
        badgeCount = 34
    ),
    BottomNavItem(
        title = "Setting",
        route = "setting",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        badgeFlag = true
    ),
)