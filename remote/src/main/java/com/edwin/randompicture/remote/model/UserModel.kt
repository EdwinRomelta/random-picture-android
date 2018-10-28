package com.edwin.randompicture.remote.model

data class UserModel(
        val id: String,
        val name: String,
        val avatarUrl: String,
        val token: String? = null
)