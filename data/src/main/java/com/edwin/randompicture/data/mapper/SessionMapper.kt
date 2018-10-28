package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.domain.model.Session
import javax.inject.Inject

class SessionMapper @Inject constructor() : Mapper<SessionEntity, Session> {

    override fun mapFromEntity(type: SessionEntity) =
            Session(id = type.id,
                    name = type.name,
                    avatarUrl = type.avatarUrl)

    override fun mapToEntity(type: Session) =
            throw UnsupportedOperationException()
}