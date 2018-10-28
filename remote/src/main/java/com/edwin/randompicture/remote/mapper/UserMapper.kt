package com.edwin.randompicture.remote.mapper

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.remote.model.UserModel
import javax.inject.Inject

class UserMapper @Inject constructor() : EntityMapper<UserModel, UserEntity> {

    override fun mapFromRemote(type: UserModel) =
            UserEntity(id = type.id,
                    name = type.name,
                    avatarUrl = type.avatarUrl,
                    token = type.token)
}