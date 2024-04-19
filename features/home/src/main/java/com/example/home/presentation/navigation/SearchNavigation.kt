package com.example.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.utils.model.Hit
import com.example.home.presentation.screens.HomeSearchRoute

const val HOME_SEARCH_ROUTE = "home_search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) = navigate(HOME_SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    onImageClick: (String) -> Unit
) {

    composable(route = HOME_SEARCH_ROUTE) {
        HomeSearchRoute(
            onImageClick = onImageClick
        )
    }
}
