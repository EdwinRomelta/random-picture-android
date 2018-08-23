package com.edwin.randompicture.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.edwin.randompicture.cache.dao.CachedPendingPostDao.Companion.FIELD_ID
import com.edwin.randompicture.cache.dao.CachedPendingPostDao.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class CachedPendingPost(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = FIELD_ID)
        val id: Long?,
        val imagePath: String,
        val caption: String,
        val createdDate: Long)