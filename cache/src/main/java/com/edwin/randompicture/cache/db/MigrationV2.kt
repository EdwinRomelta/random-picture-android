package com.edwin.randompicture.cache.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationV2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `post` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `imagePath` TEXT NOT NULL, `caption` TEXT NOT NULL, `createdDate` INTEGER NOT NULL)")
    }

}