package com.example.home.domain.repository

import com.example.utils.model.Hit
import kotlinx.coroutines.flow.Flow

interface ImageSearchRepository {

    suspend fun callImageSearch(searchKey: String): Flow<List<Hit>?>

}