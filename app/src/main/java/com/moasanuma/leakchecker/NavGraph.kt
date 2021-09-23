package com.moasanuma.leakchecker

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moasanuma.leakchecker.ui.HomeScreen
import com.moasanuma.leakchecker.ui.ResultScreen
import com.moasanuma.leakchecker.viewmodel.PassViewModel

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
            val passViewModel: PassViewModel = viewModel()
            ResultScreen(
                pass = backStackEntry.arguments?.getString("pass"),
                passViewModel = passViewModel
            )
        }
    }
}
