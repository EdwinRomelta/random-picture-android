package com.edwin.randompicture.cache.dao

import androidx.paging.DataSource
import androidx.room.*
import com.edwin.randompicture.cache.model.CachedPendingPost
import io.reactivex.Flowable


@Dao
interface CachedPendingPostDao {

    companion object {
        const val TABLE_NAME = "pending_post"
        const val FIELD_ID = "id"
        const val FIELD_STATUS = "status"
    }

    @Query("Select * from $TABLE_NAME LIMIT 1")
    fun getFirstPendingPost(): CachedPendingPost

    @Query("Select * FROM $TABLE_NAME")
    fun getPendingPosts(): DataSource.Factory<Int, CachedPendingPost>

    @Query("Select * FROM $TABLE_NAME WHERE $FIELD_ID = :id")
    fun getPendingPostById(id: Long): Flowable<CachedPendingPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPendingPost(pendingPost: CachedPendingPost): Long

    @Delete
    fun delete(cachedPendingPost: CachedPendingPost)
}