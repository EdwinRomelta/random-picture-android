package com.edwin.randompicture.cache.db

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.edwin.randompicture.cache.dao.CachedPendingPostDao
import com.edwin.randompicture.cache.model.CachedPendingPost

@Database(entities = [(CachedPendingPost::class)], version = 1)
abstract class RandomPictureDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "random-picture-database"

        fun initialize(application: Application) = Room.databaseBuilder(application,
                RandomPictureDatabase::class.java, DATABASE_NAME).build()
    }

    abstract fun cachedPendingPostDao(): CachedPendingPostDao

}