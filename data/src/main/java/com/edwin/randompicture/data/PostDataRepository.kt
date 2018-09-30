package com.edwin.randompicture.data

import com.edwin.randompicture.data.mapper.PostMapper
import com.edwin.randompicture.data.source.post.PostDataStoreFactory
import com.edwin.randompicture.domain.model.Post
import com.edwin.randompicture.domain.repository.PostRepository
import io.reactivex.Flowable
import javax.inject.Inject

class PostDataRepository @Inject constructor(private val factory: PostDataStoreFactory,
                                             private val postMapper: PostMapper) : PostRepository {

    override fun getPosts(): Flowable<List<Post>> =
            factory.retrieveRemoteDataStore().getPost()
                    .map { postEntityList ->
                        postEntityList.map { postMapper.mapFromEntity(it) }
                    }

}