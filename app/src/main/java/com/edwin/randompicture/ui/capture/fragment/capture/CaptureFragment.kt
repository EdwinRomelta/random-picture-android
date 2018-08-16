package com.edwin.randompicture.ui.capture.fragment.capture

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.CaptureFragmentBinding
import com.edwin.randompicture.util.autoCleared

class CaptureFragment : Fragment() {

    companion object {
        fun newInstance() = CaptureFragment()
    }

    var binding by autoCleared<CaptureFragmentBinding>()
    private lateinit var viewModel: CaptureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: CaptureFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.capture_fragment, container, false)
        binding = databinding
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaptureViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        binding.cameraView.start()
    }

    override fun onStop() {
        binding.cameraView.stop()
        super.onStop()
    }
}
