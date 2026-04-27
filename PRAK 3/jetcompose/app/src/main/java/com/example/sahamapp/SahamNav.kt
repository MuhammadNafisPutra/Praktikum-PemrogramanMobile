package com.example.sahamapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SahamNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("detail/{stockIndex}") { backStackEntry ->
            val indexStr = backStackEntry.arguments?.getString("stockIndex") ?: "0"
            val index = indexStr.toIntOrNull() ?: 0
            DetailScreen(stockIndex = index)
        }
    }
}