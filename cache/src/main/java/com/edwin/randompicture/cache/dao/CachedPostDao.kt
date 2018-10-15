package com.edwin.randompicture.cache.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.edwin.randompicture.cache.model.CachedPost
import io.reactivex.Flowable


@Dao
interface CachedPostDao {

    companion object {
        const val TABLE_NAME = "post"
        const val FIELD_ID = "id"
        const val FIELD_TIMESTAMP = "timeStamp"
    }

    @Query("Select * FROM $TABLE_NAME")
    fun getPosts(): Flowable<List<CachedPost>>

    @Query("Select * FROM $TABLE_NAME ORDER BY $FIELD_TIMESTAMP desc")
    fun getPostDataSource(): DataSource.Factory<Int, CachedPost>

    @Query("Select * FROM $TABLE_NAME WHERE $FIELD_ID = :id")
    fun getPostById(id: Long): Flowable<CachedPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: CachedPost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<CachedPost>)

}