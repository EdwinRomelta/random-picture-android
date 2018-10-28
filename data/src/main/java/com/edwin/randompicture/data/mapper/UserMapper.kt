package com.edwin.randompicture.data.mapper

import com.edwin.randompicture.data.model.UserEntity
import com.edwin.randompicture.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserEntity, User> {

    override fun mapFromEntity(type: UserEntity) =
            User(id = type.id,
                    name = type.name,
                    avatarUrl = type.avatarUrl)

    override fun mapToEntity(type: User) =
            UserEntity(id = type.id,
                    name = type.name,
                    avatarUrl = type.avatarUrl)
}