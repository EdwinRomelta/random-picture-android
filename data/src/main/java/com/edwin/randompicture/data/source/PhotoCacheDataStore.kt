package com.edwin.randompicture.data.source

import com.edwin.randompicture.data.model.PhotoEntity
import com.edwin.randompicture.data.repository.photo.PhotoCache
import com.edwin.randompicture.data.repository.photo.PhotoDataStore
import io.reactivex.Single
import javax.inject.Inject

class PhotoCacheDataStore @Inject constructor(

        private val photoCache: PhotoCache
) : PhotoDataStore {

    override fun savePhoto(photoEntity: PhotoEntity): Single<PhotoEntity> {
        return photoCache.savePhoto(photoEntity)
    }

}