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

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val RESULT_ROUTE = "result"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = MainDestinations.HOME_ROUTE) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreen(navController = navController)
        }
        composable(
            MainDestinations.RESULT_ROUTE + "/{pass}",
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
