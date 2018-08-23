package com.edwin.randompicture.data.model

import java.util.*

data class PendingPostEntity(
        val imagePath: String,
        val caption: String,
        val createdDate: Date
)