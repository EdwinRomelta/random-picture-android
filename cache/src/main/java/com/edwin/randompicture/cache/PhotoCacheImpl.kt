package com.edwin.randompicture.cache

import com.edwin.randompicture.cache.fao.CachedPhotoFao
import com.edwin.randompicture.cache.mapper.PhotoEntityMapper
import com.edwin.randompicture.data.model.PhotoEntity
import com.edwin.randompicture.data.repository.photo.PhotoCache
import io.reactivex.Single
import javax.inject.Inject

class PhotoCacheImpl @Inject constructor(
        private val photoEntityMapper: PhotoEntityMapper,
        private val cachedPhotoFao: CachedPhotoFao) : PhotoCache {

    override fun savePhoto(photoEntity: PhotoEntity): Single<PhotoEntity> {
        return cachedPhotoFao.savePhoto(photoEntityMapper.mapToCached(photoEntity))
                .map {
                    photoEntityMapper.mapFromCached(it)
                }
    }
}