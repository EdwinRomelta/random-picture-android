package com.edwin.randompicture.ui.screen.signup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.RegisterFragmentBinding
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.data.error.show
import com.edwin.randompicture.presentation.viewmodel.session.RegisterViewModel
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModelFactory
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.util.autoCleared
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RegisterFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: SessionViewModelFactory

    private lateinit var registerViewModel: RegisterViewModel
    var binding by autoCleared<RegisterFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: RegisterFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.register_fragment, container, false)
        binding = databinding
        binding.apply {
            onCreateDisposable.add(registerButton.clicks()
                    .subscribe {
                        registerViewModel.doRegister(emailEditText.text.toString(),
                                nameEditText.text.toString(),
                                passwordEditText.text.toString(),
                                confirmPasswordEditText.text.toString())
                    }
            )
        }
        return databinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart(onStartDisposable)
        registerViewModel.registerResponse.observe(this, Observer { resource ->
            binding.apply {
                when (resource?.status) {
                    ResourceState.SUCCESS -> {
                        activity?.finish()
                    }
                    ResourceState.LOADING -> {
                        emailTextInputLayout.error = null
                        nameTextInputLayout.error = null
                        passwordTextInputLayout.error = null
                        confirmPasswordTextInputLayout.error = null
                        progressBar.visibility = View.VISIBLE
                        registerButton.visibility = View.GONE
                    }
                    ResourceState.ERROR -> {
                        progressBar.visibility = View.GONE
                        registerButton.visibility = View.VISIBLE
                        val errorResource = resource.errorResource
                        if (null != errorResource)
                            when (errorResource) {
                                is ValidationErrorResource -> {
                                    if (errorResource.errorCode and RegisterViewModel.VALIDATION_EMPTY_EMAIL > 0) {
                                        emailTextInputLayout.error = getString(R.string.signupscreen_erroremptyemail)
                                    } else if (errorResource.errorCode and RegisterViewModel.VALIDATION_INVALID_EMAIL > 0) {
                                        emailTextInputLayout.error = getString(R.string.signupscreen_errorinvalidemail)
                                    }

                                    if (errorResource.errorCode and RegisterViewModel.VALIDATION_EMPTY_NAME > 0) {
                                        nameTextInputLayout.error = getString(R.string.registerscreen_erroremptyname)
                                    }

                                    if (errorResource.errorCode and RegisterViewModel.VALIDATION_EMPTY_PASSWORD > 0) {
                                        passwordTextInputLayout.error = getString(R.string.signupscreen_erroremptypassword)
                                    }

                                    if (errorResource.errorCode and RegisterViewModel.VALIDATION_EMPTY_CONFIRMATION_PASSWORD > 0) {
                                        confirmPasswordTextInputLayout.error = getString(R.string.registerscreen_erroremptyconfirmationpassword)
                                    } else if (errorResource.errorCode and RegisterViewModel.VALIDATION_PASSWORD_MISMATCH > 0) {
                                        confirmPasswordTextInputLayout.error = getString(R.string.registerscreen_errorpasswordmismatch)
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
        registerViewModel.registerResponse.removeObservers(this)
    }
}