package com.example.football.navigation


sealed class Screen(val route: String){

    object Home: Screen(route = "Home")

}