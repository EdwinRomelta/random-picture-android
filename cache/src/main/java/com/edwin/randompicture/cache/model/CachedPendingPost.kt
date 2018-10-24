package com.edwin.randompicture.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edwin.randompicture.cache.dao.CachedPendingPostDao.Companion.FIELD_ID
import com.edwin.randompicture.cache.dao.CachedPendingPostDao.Companion.FIELD_STATUS
import com.edwin.randompicture.cache.dao.CachedPendingPostDao.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class CachedPendingPost(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = FIELD_ID)
        val id: Long?,
        val imagePath: String,
        val caption: String,
        val createdDate: Long,
        @ColumnInfo(name = FIELD_STATUS)
        val status: Int)