package com.edwin.randompicture.cache.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object MigrationV2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `post` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `imagePath` TEXT NOT NULL, `caption` TEXT NOT NULL, `createdDate` INTEGER NOT NULL)")
    }

}