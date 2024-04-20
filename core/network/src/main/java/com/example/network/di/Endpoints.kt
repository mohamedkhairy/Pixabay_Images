package com.example.network.di

import com.example.pixabayimages.core.network.BuildConfig
import io.ktor.http.URLBuilder

object Endpoints {

    const val BASE_URL = BuildConfig.BASE_URL

    fun SEARCH_API() =
        URLBuilder(BASE_URL).apply {
            parameters.append("key", BuildConfig.API_KEY)
        }.build()

}