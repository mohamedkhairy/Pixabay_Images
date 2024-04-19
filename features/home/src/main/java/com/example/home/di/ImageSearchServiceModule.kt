package com.example.home.di


import com.example.home.data.remote.service.imageSearch.ImageSearchService
import com.example.home.data.remote.service.imageSearch.ImageSearchServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*

@Module
@InstallIn(ViewModelComponent::class)
object ImageSearchServiceModule {

    @Provides
    fun provideImageSearchService(httpClient: HttpClient): ImageSearchService {
        return ImageSearchServiceImpl(httpClient)
    }

}