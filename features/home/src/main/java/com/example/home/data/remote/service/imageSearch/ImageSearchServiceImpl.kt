package com.example.home.data.remote.service.imageSearch

import com.example.home.data.remote.dto.ImageResultResponse
import com.example.network.di.Endpoints
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class ImageSearchServiceImpl @Inject constructor(val httpClient: HttpClient): ImageSearchService {



    private val endpointUrl = Endpoints.BASE_URL
//    override suspend fun callFootballMatches(): FootballMatchesResponse {
//        return httpClient.get<FootballMatchesResponse> {
//            url(endpointUrl)
//            headers {
//                append(getApiKeyName(), getApiKeyValue())
//            }
//        }
//    }

//    val url = URLBuilder("https://api.example.com/data")
//        .parameters.append("key1","value2")


    override suspend fun callImageSearch(searchKey: String): ImageResultResponse {
        return httpClient.get<ImageResultResponse>(Endpoints.SEARCH_API()) {
//            parameter("key","")
            parameter("q",searchKey)
//            parametersOf("token", "abc123")
//            headers {
//                append(getApiKeyName(), getApiKeyValue())
//            }
        }
    }


}