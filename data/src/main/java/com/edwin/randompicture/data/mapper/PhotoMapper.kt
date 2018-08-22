package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.PhotoEntity
import com.edwin.randompicture.domain.model.Photo
import javax.inject.Inject

class PhotoMapper @Inject constructor() : Mapper<PhotoEntity, Photo> {

    override fun mapFromEntity(type: PhotoEntity) = Photo(type.folderPath, type.fileName, type.byteArray)

    override fun mapToEntity(type: Photo) = PhotoEntity(type.folderPath, type.fileName, type.byteArray)
}