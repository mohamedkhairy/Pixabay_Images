package com.example.pixabayimages.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.details.navigation.detailsScreen
import com.example.details.navigation.navigateToDetails
import com.example.home.presentation.navigation.HOME_SEARCH_ROUTE
import com.example.home.presentation.navigation.searchScreen


@Composable
fun NavigationSystem() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME_SEARCH_ROUTE,
        builder = {
            searchScreen(
                onImageClick = {
                    navController.navigateToDetails(it)
                }
            )
            detailsScreen {
                navController.popBackStack(HOME_SEARCH_ROUTE, false)
            }
        }
    )

}





