package com.edwin.randompicture.domain.interactor

import com.edwin.randompicture.domain.executor.PostExecutionThread
import com.edwin.randompicture.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class SingleUseCase<T, in Params> protected constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private var subscription = Disposables.empty()

    /**
     * Builds a [Single] which will be used when the current [SingleUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params): Single<T>

    /**
     * Executes the current use case.
     */
    fun execute(params: Params): Single<T> {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }


    fun execute(observer: DisposableSingleObserver<in T>, params: Params) {
        subscription = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler).subscribeWith(observer)
    }

    fun executeSync(params: Params): T {
        return this.buildUseCaseObservable(params).blockingGet()
    }

    /**
     * Unsubscribes from current [Disposable].
     */
    fun dispose() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

}