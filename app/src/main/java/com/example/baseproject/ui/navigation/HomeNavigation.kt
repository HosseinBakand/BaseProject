package com.example.baseproject.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.baseproject.ui.screens.home.HomeScreen
import com.example.baseproject.ui.screens.temp.TempScreen

object HomeDestination : BaseProjectDestination {
    override val route: String = "home"

}

object TempDestination : BaseProjectDestination {
    override val route: String = "temp"
}

fun NavGraphBuilder.carListGraph(navController: NavController) {
    composable(
        route = HomeDestination.route,
    ) {
        HomeScreen(){
            navController.navigate(TempDestination.route)
        }
    }
    composable(
        route = TempDestination.route,
    ) {
        TempScreen()
    }
}
