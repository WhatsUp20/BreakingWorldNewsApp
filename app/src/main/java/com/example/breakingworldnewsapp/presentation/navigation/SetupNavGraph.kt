package com.example.breakingworldnewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breakingworldnewsapp.presentation.ui.welcome.WelcomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination =  Screens.WelcomeScreen.route) {
        composable(route = Screens.WelcomeScreen.route) {
            WelcomeScreen()
        }
    }
}