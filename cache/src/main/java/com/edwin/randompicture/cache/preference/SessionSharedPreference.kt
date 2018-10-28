package com.edwin.randompicture.cache.preference

import android.content.SharedPreferences
import com.edwin.randompicture.cache.model.CachedSession
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Function4
import javax.inject.Inject

class SessionSharedPreference @Inject constructor(preferences: SharedPreferences) {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_TOKEN = "token"
        private const val KEY_NAME = "name"
        private const val KEY_AVATAR = "avatar"
    }

    private val rxSharedPreferences = RxSharedPreferences.create(preferences)

    private val id = rxSharedPreferences.getString(KEY_ID)
    private val token = rxSharedPreferences.getString(KEY_TOKEN)
    private val name = rxSharedPreferences.getString(KEY_NAME)
    private val avatar = rxSharedPreferences.getString(KEY_AVATAR)

    fun store(cachedSession: CachedSession) =
            Completable.fromCallable {
                //TODO find better solution to handle nullable on rxjava
                id.set(cachedSession.id ?: "")
                token.set(cachedSession.token ?: "")
                name.set(cachedSession.name ?: "")
                avatar.set(cachedSession.avatarUrl ?: "")
            }

    fun clear() {
        rxSharedPreferences.clear()
    }

    fun get(): Flowable<CachedSession> =
            Flowable.zip<String, String, String, String, CachedSession>(
                    id.asObservable().toFlowable(BackpressureStrategy.LATEST),
                    token.asObservable().toFlowable(BackpressureStrategy.LATEST),
                    name.asObservable().toFlowable(BackpressureStrategy.LATEST),
                    avatar.asObservable().toFlowable(BackpressureStrategy.LATEST),
                    Function4<String, String, String, String, CachedSession> { t1, t2, t3, t4 ->
                        CachedSession(id = t1.toNullable(),
                                token = t2.toNullable(),
                                name = t3.toNullable(),
                                avatarUrl = t4.toNullable())
                    })

    private fun String.toNullable(): String? = if (isEmpty() || isBlank()) null else this
}