package com.edwin.randompicture.data.model

data class UserEntity(
        val id: String,
        val name: String,
        val avatarUrl: String?,
        val token: String? = null
)