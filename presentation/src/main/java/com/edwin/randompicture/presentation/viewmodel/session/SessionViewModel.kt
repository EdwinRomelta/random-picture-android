package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.GetSession
import com.edwin.randompicture.domain.interactor.usecase.Logout
import com.edwin.randompicture.domain.model.Session
import com.edwin.randompicture.presentation.data.ActionLiveData
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.mapper.SessionMapper
import com.edwin.randompicture.presentation.model.SessionView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber

class SessionViewModel(private val getSession: GetSession,
                       private val logout: Logout,
                       private val sessionMapper: SessionMapper) : BaseViewModel() {

    private val logoutResponseLiveData = ActionLiveData<Resource<Unit>>()
    val logoutResponse: LiveData<Resource<Unit>> = logoutResponseLiveData

    private val sessionLiveData = MutableLiveData<SessionView>()
    val session: LiveData<SessionView> = sessionLiveData

    init {
        getSession.execute(SessionSubscriber(), Unit)
    }

    fun logout() {
        logoutResponseLiveData.postValue(Resource.loading())
        compositeDisposable.add(
                logout.execute(Unit)
                        .subscribeWith(LogoutSubscriber()))
    }

    override fun onCleared() {
        super.onCleared()
        getSession.dispose()
    }

    inner class SessionSubscriber : DisposableSubscriber<Session>() {

        override fun onNext(session: Session) {
            sessionLiveData.postValue(sessionMapper.mapToView(session))
        }

        override fun onError(e: Throwable) {
            sessionLiveData.postValue(null)
        }

        override fun onComplete() {
            sessionLiveData.postValue(null)
        }

    }

    inner class LogoutSubscriber : DisposableCompletableObserver() {

        override fun onError(e: Throwable) {
            logoutResponseLiveData.postValue(Resource.error(e))
        }

        override fun onComplete() {
            logoutResponseLiveData.postValue(Resource.success(Unit))
        }

    }
}