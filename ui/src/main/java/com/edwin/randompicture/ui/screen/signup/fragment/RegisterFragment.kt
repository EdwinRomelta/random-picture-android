package com.edwin.randompicture.ui.screen.signup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.RegisterFragmentBinding
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.util.autoCleared
import io.reactivex.disposables.CompositeDisposable

class RegisterFragment : BaseFragment() {

    var binding by autoCleared<RegisterFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: RegisterFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.register_fragment, container, false)
        binding = databinding
        return databinding.root
    }
}