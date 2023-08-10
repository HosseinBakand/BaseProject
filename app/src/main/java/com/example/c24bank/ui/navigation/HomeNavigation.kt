package com.example.c24bank.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.c24bank.ui.screens.home.ProductListScreen
import com.example.c24bank.ui.screens.temp.DetailScreen

object ProductListDestination : C24BankDestination {
    override val route: String = "products"

}

object DetailDestination : C24BankDestination {
    const val productIdArg = "product-id"
    override val route: String = "detail/{$productIdArg}"

    fun createRoute(productId: Int): String {
        return "detail/$productId"
    }
}

fun NavGraphBuilder.carListGraph(navController: NavController) {
    composable(
        route = ProductListDestination.route,
    ) {
        ProductListScreen() { productId ->
            navController.navigate(DetailDestination.createRoute(productId))
        }
    }
    composable(
        route = DetailDestination.route,
        arguments = listOf(
            navArgument(DetailDestination.productIdArg) { type = NavType.IntType }
        )
    ) {
        DetailScreen()
    }
}
