package com.moasanuma.leakchecker

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moasanuma.leakchecker.ui.HomeScreen
import com.moasanuma.leakchecker.ui.ResultScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable(
            "result/{pass}",
            arguments = listOf(
                navArgument("pass") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            ResultScreen(
                navController = navController,
                pass = backStackEntry.arguments?.getString("pass")
            )
        }
    }
}
