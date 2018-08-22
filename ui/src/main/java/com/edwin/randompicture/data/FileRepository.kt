package com.edwin.randompicture.data

import android.graphics.Bitmap
import android.os.Environment
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class FileRepository @Inject constructor() {

    fun create(bitmap: Bitmap): File {
        val file = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "PICTURE_${System.currentTimeMillis()}.jpeg")
        if (file.createNewFile()) {
            BufferedOutputStream(FileOutputStream(file)).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
            return file
        }
        throw IllegalAccessException("unable to create file")
    }
}