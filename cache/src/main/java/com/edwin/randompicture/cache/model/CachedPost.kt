package com.edwin.randompicture.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.edwin.randompicture.cache.dao.CachedPostDao
import java.util.*

@Entity(tableName = CachedPostDao.TABLE_NAME)
data class CachedPost(
        @PrimaryKey
        @ColumnInfo(name = CachedPostDao.FIELD_ID)
        val id: String,
        val imgPath: String,
        val text: String,
        @ColumnInfo(name = CachedPostDao.FIELD_TIMESTAMP)
        val timeStamp: Date)