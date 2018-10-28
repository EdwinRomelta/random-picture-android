package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwin.randompicture.domain.interactor.usecase.Login
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class SessionViewModelFactory @Inject constructor(private val login: Login) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}