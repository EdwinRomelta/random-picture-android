package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.LiveData
import com.edwin.randompicture.domain.interactor.usecase.Login
import com.edwin.randompicture.presentation.R
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.error.ToastErrorResource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver

class LoginViewModel(private val login: Login) : BaseViewModel() {

    companion object {
        const val VALIDATION_EMPTY_EMAIL = 10001
        const val VALIDATION_EMPTY_PASSWORD = 10002
    }

    private val loginMutableLiveData = ActionLiveData<Resource<Unit>>()
    val loginResponse: LiveData<Resource<Unit>> = loginMutableLiveData

    fun doLogin(email: String?, password: String?) {
        loginMutableLiveData.sendAction(Resource.loading())
        if (email == null || email.isEmpty()) {
            loginMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_EMPTY_EMAIL)))
            return
        }
        if (password == null || password.isEmpty()) {
            loginMutableLiveData.sendAction(Resource.error(ValidationErrorResource(VALIDATION_EMPTY_PASSWORD)))
            return
        }

        compositeDisposable.add(login.execute(login.LoginParam(email, password))
                .subscribeWith(LoginSubscriber()))
    }

    inner class LoginSubscriber : DisposableCompletableObserver() {

        override fun onComplete() {
            loginMutableLiveData.postValue(Resource.success(Unit))
        }

        override fun onError(exception: Throwable) {
            loginMutableLiveData.postValue(Resource.error(ToastErrorResource(R.string.capture_errorprocessingimage)))
        }
    }
}