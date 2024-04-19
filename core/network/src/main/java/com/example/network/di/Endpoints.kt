package com.example.network.di

import androidx.core.os.BuildCompat
import io.ktor.http.URLBuilder
import io.ktor.http.append

object Endpoints {

    const val BASE_URL = "https://pixabay.com/api/"
//    const val BASE_URL2 = BuildConfig()
    fun SEARCH_API() =
        URLBuilder(BASE_URL).apply {
            parameters.append("key", "43409992-11eeedaf8c3426666fa1dfb6d")
        }.build()




    // ?key=43409992-11eeedaf8c3426666fa1dfb6d
    // &
    // q=yellow+flowers&image_type=photo&pretty=true


//            https://pixabay.com/api/?key=43409992-11eeedaf8c3426666fa1dfb6d&q=yellow+flowers&image_type=photo&pretty=true
}