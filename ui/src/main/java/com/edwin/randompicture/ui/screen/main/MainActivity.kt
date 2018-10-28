package com.edwin.randompicture.ui.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.MainActivityBinding
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModel
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModelFactory
import com.edwin.randompicture.ui.screen.signup.SignUpActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationHeaderListener {

    companion object {
        private const val REQUEST_CODE_LOGIN = 10001
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    @Inject
    lateinit var viewModelFactory: SessionViewModelFactory

    lateinit var sessionViewModel: SessionViewModel
    lateinit var navigationHeader: NavigationHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionViewModel = ViewModelProviders.of(this, viewModelFactory).get(SessionViewModel::class.java)
        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setSupportActionBar(binding.toolbar)

        val mainNavigationController = findNavController(this, R.id.main_navigation_fragment)
        setupActionBarWithNavController(this, mainNavigationController)

        navigationHeader = NavigationHeader.addNavigationHeader(binding.navigationView, this)
    }

    override fun onStart() {
        super.onStart()
        sessionViewModel.session.observe(this, Observer {
            navigationHeader.setSession(it)
        })
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onLogin() {
        startActivityForResult(SignUpActivity.createIntent(this), REQUEST_CODE_LOGIN)
    }

}