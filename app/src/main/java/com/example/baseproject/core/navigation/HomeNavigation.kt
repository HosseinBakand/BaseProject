package com.example.baseproject.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object HomeDestination : BaseProjectDestination {
    const val marketIdArg = "chapterId"

    override val route: String = "car-list/{$marketIdArg}"

    fun createNavigationRoute(marketIdArg: String): String {
        return "car-list/$marketIdArg"
    }
}

fun NavGraphBuilder.carListGraph(navController: NavController) {
    composable(route = HomeDestination.route,
        arguments = listOf(
            navArgument(HomeDestination.marketIdArg) { type = NavType.StringType }
        )
    ) {
//        CarListScreen(
//            onBackClick = {
//                navController.navigateUp()
//            },
//            onCarClick = {
//
//            }
//        )
    }
}
