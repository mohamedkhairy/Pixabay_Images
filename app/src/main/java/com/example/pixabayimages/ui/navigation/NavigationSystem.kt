package com.example.pixabayimages.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.football.navigation.Screen
import com.example.home.presentation.navigation.HOME_SEARCH_ROUTE
import com.example.home.presentation.navigation.navigateToSearch
import com.example.home.presentation.navigation.searchScreen


@Composable
fun NavigationSystem() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME_SEARCH_ROUTE,
        builder = {
            searchScreen(
                onImageClick = { navController::navigateToSearch }
            )
        }
    )

}


//fun NavGraphBuilder.addHomeScreen(
//) {
//    composable(
//        route = Screen.Home.route,
//    ){
//        HomeScreen()
//    }
//}




