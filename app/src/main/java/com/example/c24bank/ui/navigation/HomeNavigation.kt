package com.example.c24bank.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.c24bank.ui.screens.home.ProductScreen
import com.example.c24bank.ui.screens.temp.DetailScreen

object HomeDestination : C24BankDestination {
    override val route: String = "home"

}

object TempDestination : C24BankDestination {
    override val route: String = "temp"
}

fun NavGraphBuilder.carListGraph(navController: NavController) {
    composable(
        route = HomeDestination.route,
    ) {
        ProductScreen(){
            navController.navigate(TempDestination.route)
        }
    }
    composable(
        route = TempDestination.route,
    ) {
        DetailScreen()
    }
}
