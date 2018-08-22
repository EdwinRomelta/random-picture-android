package com.edwin.randompicture.data.repository.photo

import com.edwin.randompicture.data.model.PhotoEntity
import io.reactivex.Single

interface PhotoDataStore {

    fun savePhoto(photoEntity: PhotoEntity): Single<PhotoEntity>
}