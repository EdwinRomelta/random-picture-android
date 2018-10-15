package com.edwin.randompicture.data

import android.arch.paging.DataSource
import com.edwin.randompicture.data.mapper.PostMapper
import com.edwin.randompicture.data.source.post.PostDataStoreFactory
import com.edwin.randompicture.domain.interactor.usecase.GetAndSavePost
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PostDataRepository @Inject constructor(private val factory: PostDataStoreFactory,
                                             private val postMapper: PostMapper) : PostRepository {

    override fun getPosts(): Flowable<List<Post>> =
            factory.retrieveRemoteDataStore().getPost()
                    .switchMap {
                        factory.retrieveCacheDataStore().savePost(it)
                                .andThen(factory.retrieveCacheDataStore().getPost())
                    }
                    .map { postEntityList ->
                        postEntityList.map { postMapper.mapFromEntity(it) }
                    }

    override fun getPostsByParam(param: GetAndSavePost.GetPostParam) =
            factory.retrieveRemoteDataStore().getPostsByParam(param)
                    .flatMap {
                        factory.retrieveCacheDataStore().savePost(it)
                                .toSingle { it }
                                .toFlowable()
                    }
                    .map { postEntityList ->
                        postEntityList.map { postMapper.mapFromEntity(it) }
                    }

    override fun getPostDataSource(): Single<DataSource.Factory<Int, Post>> =
            factory.retrieveCacheDataStore().getPostDataSource()
                    .map { dataSource -> dataSource.map { postMapper.mapFromEntity(it) } }

    override fun publishPost(post: Post): Single<Post> =
            factory.retrieveRemoteDataStore().publishPost(postMapper.mapToEntity(post))
                    .map { postMapper.mapFromEntity(it) }

}