package com.edwin.randompicture.ui.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(private val fragment: Fragment) : DataBindingComponent {

    override fun getFragmentBindingAdapters() = FragmentBindingAdapters(fragment)
}