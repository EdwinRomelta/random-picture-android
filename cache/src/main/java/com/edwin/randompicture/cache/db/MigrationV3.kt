package com.edwin.randompicture.cache.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationV3 : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE pending_post ADD status INTEGER NOT NULL DEFAULT 0;")
    }

}