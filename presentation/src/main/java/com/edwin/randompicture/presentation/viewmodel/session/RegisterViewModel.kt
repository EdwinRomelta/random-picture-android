package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.LiveData
import com.edwin.randompicture.domain.interactor.usecase.Register
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.util.isEmail
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver

class RegisterViewModel(private val register: Register) : BaseViewModel() {

    companion object {
        const val VALIDATION_EMPTY_EMAIL = 0b1
        const val VALIDATION_EMPTY_PASSWORD = 0b10
        const val VALIDATION_EMPTY_NAME = 0b100
        const val VALIDATION_EMPTY_CONFIRMATION_PASSWORD = 0b1000
        const val VALIDATION_PASSWORD_MISMATCH = 0b10000
        const val VALIDATION_INVALID_EMAIL = 0b100000
    }

    private val registerMutableLiveData = ActionLiveData<Resource<Unit>>()
    val registerResponse: LiveData<Resource<Unit>> = registerMutableLiveData

    fun doRegister(email: String, name: String, password: String, confirmationPassword: String) {
        registerMutableLiveData.sendAction(Resource.loading())
        var validation = 0
        if (name.isBlank()) {
            validation = validation or VALIDATION_EMPTY_NAME
        }
        if (email.isBlank()) {
            validation = validation or VALIDATION_EMPTY_EMAIL
        }
        if (password.isEmpty()) {
            validation = validation or VALIDATION_EMPTY_PASSWORD
        }
        if (confirmationPassword.isEmpty()) {
            validation = validation or VALIDATION_EMPTY_CONFIRMATION_PASSWORD
        }
        if (password != confirmationPassword) {
            validation = validation or VALIDATION_PASSWORD_MISMATCH
        }
        if (!email.isEmail()) {
            validation = validation or VALIDATION_INVALID_EMAIL
        }
        if (validation > 0) {
            registerMutableLiveData.sendAction(Resource.error(ValidationErrorResource(validation)))
        } else {
            compositeDisposable.add(register.execute(register.RegisterParam(name, email, password))
                    .subscribeWith(RegisterSubscriber()))
        }
    }

    inner class RegisterSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
            registerMutableLiveData.postValue(Resource.success(Unit))
        }

        override fun onError(exception: Throwable) {
            registerMutableLiveData.postValue(Resource.error(exception))
        }
    }
}