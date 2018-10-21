package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.domain.model.PendingPost
import javax.inject.Inject

class PendingPostMapper @Inject constructor() : Mapper<PendingPostEntity, PendingPost> {

    override fun mapFromEntity(type: PendingPostEntity) = PendingPost(
            id = type.id,
            imagePath = type.imagePath,
            caption = type.caption,
            createdDate = type.createdDate,
            status = type.status)

    override fun mapToEntity(type: PendingPost) = PendingPostEntity(
            id = type.id,
            imagePath = type.imagePath,
            caption = type.caption,
            createdDate = type.createdDate,
            status = type.status)
}