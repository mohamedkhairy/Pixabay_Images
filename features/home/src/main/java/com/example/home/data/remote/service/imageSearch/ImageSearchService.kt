package com.example.home.data.remote.service.imageSearch

import com.example.home.data.remote.dto.ImageResultResponse

interface ImageSearchService {

    suspend fun callImageSearch(searchKey: String): ImageResultResponse


}
