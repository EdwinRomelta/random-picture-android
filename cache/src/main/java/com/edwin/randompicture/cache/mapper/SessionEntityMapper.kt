package com.edwin.randompicture.cache.mapper

import com.edwin.randompicture.cache.model.CachedSession
import com.edwin.randompicture.data.model.SessionEntity
import javax.inject.Inject

class SessionEntityMapper @Inject constructor() : EntityMapper<CachedSession, SessionEntity> {

    override fun mapFromCached(type: CachedSession) =
            SessionEntity(id = type.id,
                    name = type.name,
                    token = type.token,
                    avatarUrl = type.avatarUrl)

    override fun mapToCached(type: SessionEntity) =
            CachedSession(id = type.id,
                    name = type.name,
                    token = type.token,
                    avatarUrl = type.avatarUrl)

}