package com.edwin.randompicture.data.model

import java.util.*

data class PendingPostEntity(
        val id: Long?,
        val imagePath: String,
        val caption: String,
        val createdDate: Date,
        val status: Int
)