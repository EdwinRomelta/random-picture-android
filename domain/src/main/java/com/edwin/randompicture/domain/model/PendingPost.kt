package com.edwin.randompicture.domain.model

import java.util.*

data class PendingPost(
        val id: Long? = null,
        val imagePath: String,
        val caption: String,
        val createdDate: Date,
        val status: Int = STATUS_IDLE
) {

    companion object {
        const val STATUS_IDLE = 0
        const val STATUS_UPLOADING = 1
        const val STATUS_FAILED = -1
    }

    constructor(imagePath: String, caption: String, createdDate: Date) :
            this(null, imagePath, caption, createdDate)
}