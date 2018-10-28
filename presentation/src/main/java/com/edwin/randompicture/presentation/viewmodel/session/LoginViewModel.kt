package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.LiveData
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.util.isEmail
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver

class LoginViewModel(private val login: Login) : BaseViewModel() {

    companion object {
        const val VALIDATION_EMPTY_EMAIL = 0b1
        const val VALIDATION_EMPTY_PASSWORD = 0b10
        const val VALIDATION_INVALID_EMAIL = 0b100
    }

    private val loginMutableLiveData = ActionLiveData<Resource<Unit>>()
    val loginResponse: LiveData<Resource<Unit>> = loginMutableLiveData

    fun doLogin(email: String, password: String) {
        loginMutableLiveData.sendAction(Resource.loading())
        var validation = 0
        if (email.isBlank()) {
            validation = validation or VALIDATION_EMPTY_EMAIL
        }
        if (password.isEmpty()) {
            validation = validation or VALIDATION_EMPTY_PASSWORD
        }
        if (!email.isEmail()) {
            validation = validation or VALIDATION_INVALID_EMAIL
        }
        if (validation > 0) {
            loginMutableLiveData.sendAction(Resource.error(ValidationErrorResource(validation)))
        } else {
            compositeDisposable.add(login.execute(login.LoginParam(email, password))
                    .subscribeWith(LoginSubscriber()))
        }
    }

    inner class LoginSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
            loginMutableLiveData.postValue(Resource.success(Unit))
        }

        override fun onError(exception: Throwable) {
            loginMutableLiveData.postValue(Resource.error(exception))
        }
    }
}