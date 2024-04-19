package com.example.home.di

import com.example.database.dbManager.HitDao
import com.example.home.data.mapper.ImageEntityMapper
import com.example.home.data.mapper.ImageEntityToDomainMapper
import com.example.home.data.remote.service.imageSearch.ImageSearchService
import com.example.home.data.repository.ImageSearchRepositoryImp
import com.example.home.domain.repository.ImageSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ImageSearchRepositoryModule {

    @Provides
    fun provideImageSearchRepository(
        service: ImageSearchService,
        hitDao: HitDao,
        entityMapper: ImageEntityMapper,
        entityDomainMapper: ImageEntityToDomainMapper,
    ) : ImageSearchRepository =
         ImageSearchRepositoryImp(service, hitDao, entityMapper, entityDomainMapper)


}
