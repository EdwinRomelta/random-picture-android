package com.edwin.randompicture.ui.screen.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.MainActivityBinding
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModel
import com.edwin.randompicture.presentation.viewmodel.session.SessionViewModelFactory
import com.edwin.randompicture.ui.screen.signup.SignUpActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.alert
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationHeaderListener {

    companion object {
        private const val REQUEST_CODE_LOGIN = 10001
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    @Inject
    lateinit var viewModelFactory: SessionViewModelFactory


    lateinit var navController: NavController
    lateinit var binding: MainActivityBinding
    lateinit var sessionViewModel: SessionViewModel
    lateinit var navigationHeader: NavigationHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionViewModel = ViewModelProviders.of(this, viewModelFactory).get(SessionViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setSupportActionBar(binding.toolbar)

        navController = findNavController(this, R.id.main_navigation_fragment)
        setupActionBarWithNavController(this, navController)

        navigationHeader = NavigationHeader.addNavigationHeader(binding.navigationView, this)
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_logout -> {
                    alert {
                        messageResource = R.string.dialog_logoutconfirmation

                        positiveButton(R.string.all_yes) { _ ->
                            sessionViewModel.logout()
                        }
                        negativeButton(R.string.all_no) { dialog ->
                            dialog.cancel()
                        }
                    }.show()
                }
                else -> {

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()
        sessionViewModel.session.observe(this, Observer {
            navigationHeader.setSession(it)
            binding.apply {
                navigationView.menu.clear()
                if (it != null) {
                    navigationView.inflateMenu(R.menu.logged_menu)
                } else {
                    navigationView.inflateMenu(R.menu.anonym_menu)
                }
            }
        })
        sessionViewModel.logoutResponse.observe(this, Observer {
            when (it?.status) {
                ResourceState.LOADING -> {

                }
                ResourceState.SUCCESS -> {
                    navController.navigate(R.id.action_mainFragment_to_splashActivity)
                    finish()
                }
                ResourceState.ERROR -> {

                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        sessionViewModel.logoutResponse.removeObservers(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onLogin() {
        startActivityForResult(SignUpActivity.createIntent(this), REQUEST_CODE_LOGIN)
    }

}