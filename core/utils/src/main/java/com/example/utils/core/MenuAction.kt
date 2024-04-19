package com.example.utils.core

sealed class MenuAction(val event: String){

    object All: MenuAction(event = "All")

    object Favorites: MenuAction(event = "Favorites")

}