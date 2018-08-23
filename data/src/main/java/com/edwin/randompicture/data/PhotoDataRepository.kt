package com.edwin.randompicture.data

import com.edwin.randompicture.data.mapper.PhotoMapper
import com.edwin.randompicture.data.source.photo.PhotoDataStoreFactory
import com.edwin.randompicture.domain.model.Photo
import com.edwin.randompicture.domain.repository.PhotoRepository
import io.reactivex.Single
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
        private val factory: PhotoDataStoreFactory,
        private val photoMapper: PhotoMapper
) : PhotoRepository {

    override fun savePhoto(photo: Photo): Single<Photo> =
            factory.retrieveCacheDataStore()
                    .savePhoto(photoMapper.mapToEntity(photo))
                    .map { photoMapper.mapFromEntity(it) }

}