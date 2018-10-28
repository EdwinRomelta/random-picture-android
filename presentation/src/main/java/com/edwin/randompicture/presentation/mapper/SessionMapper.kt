package com.edwin.randompicture.presentation.mapper

import com.edwin.randompicture.domain.model.Session
import com.edwin.randompicture.presentation.model.SessionView
import javax.inject.Inject

class SessionMapper @Inject constructor() : Mapper<SessionView?, Session> {

    override fun mapToView(type: Session): SessionView? {
        val id = type.id
        val name = type.name
        val avatarUrl = type.avatarUrl
        return if (id != null && name != null) {
            SessionView(
                    id = id,
                    name = name,
                    avatarUrl = avatarUrl)
        } else {
            null
        }
    }
}