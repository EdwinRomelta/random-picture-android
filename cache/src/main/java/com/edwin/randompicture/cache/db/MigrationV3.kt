package com.edwin.randompicture.cache.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object MigrationV3 : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE pending_post ADD status INTEGER NOT NULL DEFAULT 0;")
    }

}