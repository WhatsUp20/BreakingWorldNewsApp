package com.example.breakingworldnewsapp.presentation.navigation

sealed class Screens(val route: String) {
    object WelcomeScreen: Screens(route = "welcome_screen")
    object DetailScreen: Screens(route = "detail_screen")
}
