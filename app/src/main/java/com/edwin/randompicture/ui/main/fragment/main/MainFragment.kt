package com.edwin.randompicture.ui.main.fragment.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.MainFragmentBinding
import com.edwin.randompicture.di.Injectable
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: MainFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        databinding.onCapture = View.OnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_captureFragment)
        }
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

}
