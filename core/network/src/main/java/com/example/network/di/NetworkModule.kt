package com.example.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun client(): HttpClient {
        return HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json  {
                            ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                            coerceInputValues = true
                            explicitNulls = false
                        }
                    )
                }
                install(Logging){
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }

    }
}