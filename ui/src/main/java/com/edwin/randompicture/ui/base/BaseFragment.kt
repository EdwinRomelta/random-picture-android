package com.edwin.randompicture.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    private val onCreateDisposable: CompositeDisposable = CompositeDisposable()
    private val onStartDisposable: CompositeDisposable = CompositeDisposable()
    private val onResumeDisposable: CompositeDisposable = CompositeDisposable()

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return onCreateView(onCreateDisposable, inflater, container, savedInstanceState)
    }

    open fun onCreateView(onCreateDisposable: CompositeDisposable,
                          inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return null
    }

    final override fun onStart() {
        super.onStart()
        onStart(onStartDisposable)
    }

    open fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart()
    }

    final override fun onResume() {
        super.onResume()
        onResume(onResumeDisposable)
    }

    open fun onResume(onResumeDisposable: CompositeDisposable) {
        super.onStart()
    }

    override fun onPause() {
        onResumeDisposable.dispose()
        super.onPause()
    }

    override fun onStop() {
        onStartDisposable.dispose()
        super.onStop()
    }

    override fun onDestroyView() {
        onCreateDisposable.clear()
        super.onDestroyView()
    }
}