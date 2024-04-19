package com.example.home.di


import com.example.database.dbManager.DatabaseManager
import com.example.database.dbManager.HitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CachedHitDaoModule {

    @Provides
    @Singleton
    fun provideCachedHitDao(database: DatabaseManager) : HitDao =
        database.cachedHitDao()

}