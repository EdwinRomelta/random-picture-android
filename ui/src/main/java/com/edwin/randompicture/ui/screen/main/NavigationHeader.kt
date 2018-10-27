package com.edwin.randompicture.ui.screen.main

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.NavigationHeaderBinding
import com.google.android.material.navigation.NavigationView


class NavigationHeader private constructor(navigationHeaderBinding: NavigationHeaderBinding,
                                           private val navigationHeaderListener: NavigationHeaderListener) {

    companion object {
        fun addNavigationHeader(navigationView: NavigationView, navigationHeaderListener: NavigationHeaderListener): NavigationHeader {
            val binding = DataBindingUtil.inflate<NavigationHeaderBinding>(LayoutInflater.from(navigationView.context), R.layout.navigation_header, navigationView, false)
            navigationView.addHeaderView(binding.root)
            return NavigationHeader(binding, navigationHeaderListener)
        }
    }

    init {
        navigationHeaderBinding.nameTextView.setOnClickListener {
            navigationHeaderListener.onLogin()
        }
    }

    fun setUser() {

    }
}

interface NavigationHeaderListener {

    fun onLogin()
}