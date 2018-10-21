package com.edwin.randompicture.cache

import android.arch.paging.DataSource
import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.cache.mapper.PostEntityMapper
import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PostCacheImpl @Inject constructor(
        randomPictureDatabase: RandomPictureDatabase,
        private val postEntityMapper: PostEntityMapper) : PostCache {

    private val cachedPostDao = randomPictureDatabase.cachedPostDao()

    override fun savePost(postEntity: PostEntity): Completable =
            Completable.fromCallable {
                cachedPostDao.insertPost(postEntityMapper.mapToCached(postEntity))
            }

    override fun savePosts(postEntity: List<PostEntity>): Completable =
            Completable.defer {
                val cachePosts = postEntity.map { postEntityMapper.mapToCached(it) }
                try {
                    cachedPostDao.insertPosts(cachePosts)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Completable.complete()
            }

    override fun getPost(): Flowable<List<PostEntity>> =
            cachedPostDao.getPosts()
                    .map { cachedPosts ->
                        cachedPosts.map { postEntityMapper.mapFromCached(it) }
                    }

    override fun clearSavePost(postEntity: List<PostEntity>): Completable =
            Completable.fromCallable {
                val cachePosts = postEntity.map { postEntityMapper.mapToCached(it) }
                cachedPostDao.clearSavePosts(cachePosts)
            }

    override fun getPostDataSource(): Single<DataSource.Factory<Int, PostEntity>> =
            Single.just(cachedPostDao.getPostDataSource()
                    .map { postEntityMapper.mapFromCached(it) })
}