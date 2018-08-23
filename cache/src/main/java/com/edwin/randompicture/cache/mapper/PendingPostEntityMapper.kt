package com.edwin.randompicture.cache.mapper

import com.edwin.randompicture.cache.model.CachedPendingPost
import com.edwin.randompicture.data.model.PendingPostEntity
import java.util.*
import javax.inject.Inject

class PendingPostEntityMapper @Inject constructor() : EntityMapper<CachedPendingPost, PendingPostEntity> {

    override fun mapFromCached(type: CachedPendingPost) =
            PendingPostEntity(type.imagePath, type.caption, Date(type.createdDate))

    override fun mapToCached(type: PendingPostEntity) =
            CachedPendingPost(null, type.imagePath, type.caption, type.createdDate.time)

}