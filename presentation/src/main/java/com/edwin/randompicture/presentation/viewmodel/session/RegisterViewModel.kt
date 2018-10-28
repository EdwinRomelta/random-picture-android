package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.LiveData
import com.edwin.randompicture.domain.interactor.usecase.Register
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver

class RegisterViewModel(private val register: Register) : BaseViewModel() {

    companion object {
        const val VALIDATION_EMPTY_EMAIL = 10001
        const val VALIDATION_EMPTY_PASSWORD = 10002
        const val VALIDATION_EMPTY_NAME = 10003
        const val VALIDATION_PASSWORD_MISMATCH = 10004
    }

    private val registerMutableLiveData = ActionLiveData<Resource<Unit>>()
    val registerResponse: LiveData<Resource<Unit>> = registerMutableLiveData

    fun doRegister(email: String?, name: String?, password: String?, confirmationPassword: String?) {
        registerMutableLiveData.sendAction(Resource.loading())
        if (name == null || name.isEmpty()) {
            registerMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_EMPTY_NAME)))
            return
        }
        if (email == null || email.isEmpty()) {
            registerMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_EMPTY_EMAIL)))
            return
        }
        if (password == null || password.isEmpty()) {
            registerMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_EMPTY_PASSWORD)))
            return
        }
        if (confirmationPassword == null || password != confirmationPassword) {
            registerMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_PASSWORD_MISMATCH)))
            return
        }

        compositeDisposable.add(register.execute(register.RegisterParam(name, email, password))
                .subscribeWith(RegisterSubscriber()))
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