package com.edwin.randompicture.remote.mapper

import com.edwin.randompicture.data.model.SessionEntity
import com.edwin.randompicture.remote.model.UserModel
import javax.inject.Inject

class UserMapper @Inject constructor() : EntityMapper<UserModel, SessionEntity> {

    override fun mapFromRemote(type: UserModel) =
            SessionEntity(id = type.id,
                    name = type.name,
                    avatarUrl = type.avatarUrl,
                    token = type.token)
}