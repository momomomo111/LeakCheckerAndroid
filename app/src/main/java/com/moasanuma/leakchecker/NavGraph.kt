package com.moasanuma.leakchecker

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moasanuma.leakchecker.ui.HomeScreen
import com.moasanuma.leakchecker.viewmodel.PassViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {

        composable("home") {
            val passViewModel = hiltViewModel<PassViewModel>()
            HomeScreen(navController = navController, passViewModel = passViewModel)
        }
    }
}
