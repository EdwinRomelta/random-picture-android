package com.edwin.randompicture.cache

import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.cache.mapper.PostEntityMapper
import com.edwin.randompicture.data.model.PostEntity
import com.edwin.randompicture.data.repository.post.PostCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PostCacheImpl @Inject constructor(
        randomPictureDatabase: RandomPictureDatabase,
        private val postEntityMapper: PostEntityMapper) : PostCache {

    private val cachedPostDao = randomPictureDatabase.cachedPostDao()

    override fun savePost(postEntity: List<PostEntity>): Completable =
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
}