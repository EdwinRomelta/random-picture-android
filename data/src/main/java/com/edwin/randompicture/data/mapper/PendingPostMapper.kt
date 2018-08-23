package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.PendingPostEntity
import com.edwin.randompicture.domain.model.PendingPost
import javax.inject.Inject

class PendingPostMapper @Inject constructor() : Mapper<PendingPostEntity, PendingPost> {

    override fun mapFromEntity(type: PendingPostEntity) = PendingPost(type.imagePath, type.caption, type.createdDate)

    override fun mapToEntity(type: PendingPost) = PendingPostEntity(type.imagePath, type.caption, type.createdDate)
}