package com.edwin.randompicture.cache.fao

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.edwin.randompicture.cache.model.CachedPhoto
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


class CachedPhotoFao @Inject constructor() {

    companion object {
        const val IMAGE_QUALITY = 95
        val COMPRESSION_FORMAT = Bitmap.CompressFormat.JPEG
    }

    @Throws(IOException::class)
    fun savePhoto(cachedPhoto: CachedPhoto): Single<CachedPhoto> {
        return Single.fromCallable<CachedPhoto> {
            val savedCachedPhoto = cachedPhoto.copy(
                    folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path,
                    fileName = "${System.currentTimeMillis()}.jpg")
            val bitmap = BitmapFactory.decodeByteArray(cachedPhoto.byteArray, 0, cachedPhoto.byteArray.size)
            FileOutputStream(File(savedCachedPhoto.folderPath, savedCachedPhoto.fileName)).use {
                bitmap.compress(COMPRESSION_FORMAT, IMAGE_QUALITY, it)
                return@fromCallable savedCachedPhoto
            }
        }
    }
}