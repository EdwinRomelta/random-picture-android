package com.edwin.randompicture.ui.binding

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment


/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(private val fragment: Fragment) : DataBindingComponent {

    override fun getFragmentBindingAdapters() = FragmentBindingAdapters(fragment)
}