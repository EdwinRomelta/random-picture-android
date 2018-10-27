package com.edwin.randompicture.ui.screen.signup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.LoginFragmentBinding
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.util.autoCleared
import io.reactivex.disposables.CompositeDisposable

class LoginFragment : BaseFragment() {

    var binding by autoCleared<LoginFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: LoginFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.login_fragment, container, false)
        binding = databinding
        return databinding.root
    }
}