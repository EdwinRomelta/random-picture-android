package com.edwin.randompicture.cache.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.edwin.randompicture.cache.dao.CachedPendingPostDao
import com.edwin.randompicture.cache.dao.CachedPostDao
import com.edwin.randompicture.cache.db.converter.DateConverter
import com.edwin.randompicture.cache.model.CachedPendingPost
import com.edwin.randompicture.cache.model.CachedPost

private const val DATABASE_VERSION = 3

@Database(entities = [
    CachedPendingPost::class,
    CachedPost::class],
        version = DATABASE_VERSION)
@TypeConverters(value = [DateConverter::class])
abstract class RandomPictureDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "random-picture-database"

        fun initialize(application: Application) =
                Room.databaseBuilder(application, RandomPictureDatabase::class.java, DATABASE_NAME)
                        .addMigrations(
                                MigrationV2,
                                MigrationV3)
                        .build()
    }

    abstract fun cachedPendingPostDao(): CachedPendingPostDao

    abstract fun cachedPostDao(): CachedPostDao

}