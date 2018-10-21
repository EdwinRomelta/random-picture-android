package com.edwin.randompicture.cache.mapper

import com.edwin.randompicture.cache.model.CachedPendingPost
import com.edwin.randompicture.data.model.PendingPostEntity
import java.util.*
import javax.inject.Inject

class PendingPostEntityMapper @Inject constructor() : EntityMapper<CachedPendingPost, PendingPostEntity> {

    override fun mapFromCached(type: CachedPendingPost) =
            PendingPostEntity(
                    id = type.id,
                    imagePath = type.imagePath,
                    caption = type.caption,
                    createdDate = Date(type.createdDate),
                    status = type.status)

    override fun mapToCached(type: PendingPostEntity) =
            CachedPendingPost(id = type.id,
                    imagePath = type.imagePath,
                    caption = type.caption,
                    createdDate = type.createdDate.time,
                    status = type.status)

}