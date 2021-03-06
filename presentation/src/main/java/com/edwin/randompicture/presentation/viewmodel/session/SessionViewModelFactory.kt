package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.GetSession
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.domain.interactor.usecase.Logout
import com.edwin.randompicture.domain.interactor.usecase.Register
import com.edwin.randompicture.presentation.mapper.SessionMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class SessionViewModelFactory @Inject constructor(
        private val getSession: GetSession,
        private val register: Register,
        private val login: Login,
        private val logout: Logout,
        private val sessionMapper: SessionMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            return SessionViewModel(getSession, logout, sessionMapper) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(register) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}