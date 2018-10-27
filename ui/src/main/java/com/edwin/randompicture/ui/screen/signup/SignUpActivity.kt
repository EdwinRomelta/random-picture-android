package com.edwin.randompicture.ui.screen.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.SignupActivityBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        fun createIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: SignupActivityBinding = DataBindingUtil.setContentView(this, R.layout.signup_activity)
        setSupportActionBar(binding.toolbar)

        val signupNavigationController = Navigation.findNavController(this, R.id.signup_navigation_fragment)
        NavigationUI.setupActionBarWithNavController(this, signupNavigationController)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}