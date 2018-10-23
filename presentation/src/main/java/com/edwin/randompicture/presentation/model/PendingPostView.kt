package com.edwin.randompicture.presentation.model

data class PendingPostView(
        val id: Long? = null,
        val imagePath: String,
        val caption: String? = null,
        val status: Int? = null)