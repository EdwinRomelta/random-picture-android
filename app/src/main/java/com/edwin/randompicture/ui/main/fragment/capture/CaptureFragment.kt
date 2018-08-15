package com.edwin.randompicture.ui.main.fragment.capture

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.edwin.randompicture.R
import kotlinx.android.synthetic.main.capture_fragment.*

class CaptureFragment : Fragment() {

    companion object {
        fun newInstance() = CaptureFragment()
    }

    private lateinit var viewModel: CaptureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.capture_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CaptureViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        cameraView.start()
    }

    override fun onStop() {
        cameraView.stop()
        super.onStop()
    }
}
