package com.example.breakingworldnewsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.breakingworldnewsapp.presentation.ui.detail.DetailScreen
import com.example.breakingworldnewsapp.presentation.ui.welcome.WelcomeScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object MainDestination {
    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail"
    const val FAVORITES_SCREEN = "favorites"
}

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = MainDestination.HOME_SCREEN) {
        composable(route = MainDestination.HOME_SCREEN) {
            WelcomeScreen { imageUrl, title, full_desc, source ->
                val image = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
                navController.navigate(route = "${MainDestination.DETAIL_SCREEN}/$image&$title&$full_desc&$source")
            }
        }
        composable(route = "${MainDestination.DETAIL_SCREEN}/{image}&{title}&{full_desc}&{source}", arguments = listOf(
            navArgument("image") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("title") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("full_desc") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("source") {
                type = NavType.StringType
                defaultValue = ""
            }

        )) { backStackEntry ->
            val image = backStackEntry.arguments?.getString("image")
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val fullDesc = backStackEntry.arguments?.getString("full_desc") ?: ""
            val source = backStackEntry.arguments?.getString("source") ?: ""

            DetailScreen(
                image = image,
                title = title,
                fullDesc = fullDesc,
                sourceName = source
            ) {
                navController.popBackStack()
            }
        }
    }
}