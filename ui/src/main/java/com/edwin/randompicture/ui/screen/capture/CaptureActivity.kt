package com.edwin.randompicture.ui.screen.capture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.CaptureActivityBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class CaptureActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: CaptureActivityBinding = DataBindingUtil.setContentView(this, R.layout.capture_activity)
        setSupportActionBar(binding.toolbar)

        val captureNavigationController = Navigation.findNavController(this, R.id.capture_navigation_fragment)
        setupActionBarWithNavController(captureNavigationController)
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = dispatchingAndroidInjector

}