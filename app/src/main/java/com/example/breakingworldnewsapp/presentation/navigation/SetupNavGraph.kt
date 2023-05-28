package com.example.breakingworldnewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.breakingworldnewsapp.presentation.ui.detail.DetailScreen
import com.example.breakingworldnewsapp.presentation.ui.welcome.WelcomeScreen
import com.example.breakingworldnewsapp.presentation.ui.welcome.WelcomeScreenViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = "welcome_screen") {
        composable(route = "welcome_screen") {
            WelcomeScreen(viewModel = WelcomeScreenViewModel()) { title, fullDesc, link ->
                runCatching {
                    val encoderUrl = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
                    navController.navigate(route = "detail_screen/$title&$fullDesc&$encoderUrl")
                }
            }
        }
        composable(route = "detail_screen/{title}&{fullDesc}&{link}", arguments = listOf(
            navArgument("title") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("fullDesc") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("link") {
                type = NavType.StringType
                defaultValue = ""
            }

        )) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val fullDesc = backStackEntry.arguments?.getString("fullDesc")
            val link = backStackEntry.arguments?.getString("link")
            DetailScreen(title = title ?: "Ошибочка", fullDesc = fullDesc ?: "Ошибочка описание", link = link ?: " ")
        }
    }
}