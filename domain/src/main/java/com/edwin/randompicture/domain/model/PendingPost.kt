package com.edwin.randompicture.domain.model

import java.util.*

data class PendingPost(
        val imagePath: String,
        val caption: String,
        val createdDate: Date
)