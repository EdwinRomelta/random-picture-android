package com.edwin.randompicture.domain.repository

import com.edwin.randompicture.domain.model.Photo
import io.reactivex.Single

interface PhotoRepository {

    fun savePhoto(photo: Photo): Single<Photo>

}