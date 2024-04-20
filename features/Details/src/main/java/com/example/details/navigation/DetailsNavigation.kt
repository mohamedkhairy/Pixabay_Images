package com.example.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.details.screens.DetailsRoute
import com.example.utils.core.jsonParse
import com.example.utils.model.Hit

const val DETAILS_ROUTE_BASE = "details_route"

const val IMAGE_DETAILS_ARG = "IMAGE_DETAILS_ARG"

const val DETAILS_ROUTE = "$DETAILS_ROUTE_BASE?$IMAGE_DETAILS_ARG={$IMAGE_DETAILS_ARG}"

fun NavController.navigateToDetails(imageHit: String?, navOptions: NavOptions? = null) {
    val route = if (!imageHit.isNullOrEmpty()) {
        "${DETAILS_ROUTE_BASE}?${IMAGE_DETAILS_ARG}=$imageHit"
    } else {
        DETAILS_ROUTE_BASE
    }
    navigate(route, navOptions)
}


fun NavGraphBuilder.detailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(IMAGE_DETAILS_ARG) {
                type = NavType.StringType
            },
        ),
    ) {

        val hit = it.arguments?.getString(IMAGE_DETAILS_ARG)
        DetailsRoute(
            imageHit = hit?.jsonParse(Hit::class.java),
            onBackClick = onBackClick
        )
    }
}
