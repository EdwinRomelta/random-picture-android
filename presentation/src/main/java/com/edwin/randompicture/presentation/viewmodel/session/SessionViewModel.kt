package com.edwin.randompicture.presentation.viewmodel.session

import androidx.lifecycle.MutableLiveData
import com.edwin.randompicture.domain.interactor.usecase.GetSession
import com.edwin.randompicture.domain.model.Session
import com.edwin.randompicture.presentation.mapper.SessionMapper
import com.edwin.randompicture.presentation.model.SessionView
import com.edwin.randompicture.presentation.viewmodel.BaseViewModel
import io.reactivex.subscribers.DisposableSubscriber

class SessionViewModel(private val getSession: GetSession,
                       private val sessionMapper: SessionMapper) : BaseViewModel() {

    private val sessionLiveData = MutableLiveData<SessionView>()
    val session = sessionLiveData

    init {
        getSession.execute(SessionSubscriber(), Unit)
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
}