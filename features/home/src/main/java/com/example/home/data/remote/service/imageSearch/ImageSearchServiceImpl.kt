package com.example.home.data.remote.service.imageSearch

import com.example.home.data.remote.dto.ImageResultResponse
import com.example.network.di.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class ImageSearchServiceImpl @Inject constructor(val httpClient: HttpClient) : ImageSearchService {

    override suspend fun callImageSearch(searchKey: String): ImageResultResponse {
        return httpClient.get<ImageResultResponse>(Endpoints.SEARCH_API()) {
            parameter("q", searchKey)
        }
    }


}