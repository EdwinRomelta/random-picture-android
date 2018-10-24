package com.edwin.randompicture.ui.binding

import androidx.databinding.DataBindingComponent


/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(private val fragment: androidx.fragment.app.Fragment) : DataBindingComponent {

    override fun getFragmentBindingAdapters() = FragmentBindingAdapters(fragment)
}