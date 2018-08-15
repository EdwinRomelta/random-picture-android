package com.edwin.randompicture.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.edwin.randompicture.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavController.OnNavigatedListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val mainNavigationController = findNavController(this, R.id.mainNavigationFragment)
        mainNavigationController.addOnNavigatedListener(this)
        setupActionBarWithNavController(mainNavigationController)
    }

    override fun onDestroy() {
        val mainNavigationController = findNavController(this, R.id.mainNavigationFragment)
        mainNavigationController.removeOnNavigatedListener(this)
        super.onDestroy()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        when (destination.id) {
            R.id.captureFragment -> toolbar.visibility = GONE
            else -> toolbar.visibility = VISIBLE
        }
    }
}