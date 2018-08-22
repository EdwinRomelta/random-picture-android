package com.edwin.randompicture.cache.mapper

import com.edwin.randompicture.cache.model.CachedPhoto
import com.edwin.randompicture.data.model.PhotoEntity
import javax.inject.Inject

class PhotoEntityMapper @Inject constructor() : EntityMapper<CachedPhoto, PhotoEntity> {

    override fun mapFromCached(type: CachedPhoto) =
            PhotoEntity(type.folderPath, type.fileName, type.byteArray)

    override fun mapToCached(type: PhotoEntity) =
            CachedPhoto(type.folderPath, type.fileName, type.byteArray)

}