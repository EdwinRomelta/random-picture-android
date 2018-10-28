package com.edwin.randompicture.ui.screen.signup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.LoginFragmentBinding
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.data.error.show
import com.edwin.randompicture.presentation.viewmodel.session.LoginViewModel
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModelFactory
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.util.autoCleared
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: SessionViewModelFactory

    private lateinit var loginViewModel: LoginViewModel
    var binding by autoCleared<LoginFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: LoginFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.login_fragment, container, false)
        binding = databinding
        binding.apply {
            onCreateDisposable.add(loginButton.clicks()
                    .subscribe {
                        loginViewModel.doLogin(emailEditText.text.toString(), passwordEditText.text.toString())
                    }
            )
            onCreateDisposable.add(registerButton.clicks()
                    .subscribe {
                        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                    }
            )
        }
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart(onStartDisposable)
        loginViewModel.loginResponse.observe(this, Observer { resource ->
            binding.apply {
                when (resource?.status) {
                    ResourceState.SUCCESS -> {
                        activity?.finish()
                    }
                    ResourceState.LOADING -> {
                        emailTextInputLayout.error = null
                        passwordTextInputLayout.error = null
                        progressBar.visibility = VISIBLE
                        loginButton.visibility = GONE
                        registerButton.visibility = GONE
                    }
                    ResourceState.ERROR -> {
                        progressBar.visibility = GONE
                        loginButton.visibility = VISIBLE
                        registerButton.visibility = VISIBLE
                        val errorResource = resource.errorResource
                        if (null != errorResource)
                            when (errorResource) {
                                is ValidationErrorResource -> {
                                    if (errorResource.errorCode and LoginViewModel.VALIDATION_EMPTY_EMAIL > 0) {
                                        emailTextInputLayout.error = getString(R.string.signupscreen_erroremptyemail)
                                    } else if (errorResource.errorCode and LoginViewModel.VALIDATION_INVALID_EMAIL > 0) {
                                        emailTextInputLayout.error = getString(R.string.signupscreen_errorinvalidemail)
                                    }

                                    if (errorResource.errorCode and LoginViewModel.VALIDATION_EMPTY_PASSWORD > 0) {
                                        passwordTextInputLayout.error = getString(R.string.signupscreen_erroremptypassword)
                                    }
                                }
                                else -> context?.let { errorResource.show(it) }
                            }
                    }
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        loginViewModel.loginResponse.removeObservers(this)
    }
}