package com.example.home.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResultResponse(
    @SerialName("hits")
    val hitResponses: List<HitResponse>?,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)