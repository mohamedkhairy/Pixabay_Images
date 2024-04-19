package com.example.home.data.mapper

import com.example.database.entity.HitCachedEntity
import com.example.home.data.remote.dto.ImageResultResponse
import com.example.utils.mapper.BaseMapper
import javax.inject.Inject


class ImageEntityMapper @Inject constructor(): BaseMapper<ImageResultResponse, List<HitCachedEntity>?> {
    override fun map(model: ImageResultResponse): List<HitCachedEntity>? =
        model.hitResponses?.map {
            HitCachedEntity(
                collections = it.collections,
                comments = it.comments,
                downloads = it.downloads,
                id = it.id,
                imageHeight = it.imageHeight,
                imageSize = it.imageSize,
                imageWidth = it.imageWidth,
                largeImageURL = it.largeImageURL,
                likes = it.likes,
                pageURL = it.pageURL,
                previewHeight = it.previewHeight,
                previewURL = it.previewURL,
                previewWidth = it.previewWidth,
                tags = it.tags,
                type = it.type,
                user = it.user,
                userId = it.userId,
                userImageURL = it.userImageURL,
                views = it.views,
                webformatHeight = it.webformatHeight,
                webformatURL = it.webformatURL,
                webformatWidth = it.webformatWidth
            )
        }

}
