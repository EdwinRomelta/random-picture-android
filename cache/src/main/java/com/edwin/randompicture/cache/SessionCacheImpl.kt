package com.edwin.randompicture.cache

import com.edwin.randompicture.cache.db.RandomPictureDatabase
import com.edwin.randompicture.cache.mapper.SessionEntityMapper
import com.edwin.randompicture.cache.preference.SessionSharedPreference
import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.data.repository.session.SessionCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class SessionCacheImpl @Inject constructor(
        private val randomPictureDatabase: RandomPictureDatabase,
        private val sessionSharedPreference: SessionSharedPreference,
        private val sessionMapper: SessionEntityMapper
) : SessionCache {

    override fun store(sessionEntity: SessionEntity): Completable =
            sessionSharedPreference.store(sessionMapper.mapToCached(sessionEntity))

    override fun get(): Flowable<SessionEntity> =
            sessionSharedPreference.get()
                    .map { sessionMapper.mapFromCached(it) }

    override fun clear(): Completable =
            sessionSharedPreference.clear()
                    .andThen(Completable.fromCallable {
                        randomPictureDatabase.clearAllTables()
                    })
}