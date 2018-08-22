package com.edwin.randompicture.ui.main.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.base.BaseFragment
import com.edwin.randompicture.databinding.MainFragmentBinding
import com.edwin.randompicture.util.autoCleared
import io.reactivex.disposables.CompositeDisposable

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    var binding by autoCleared<MainFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: MainFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        databinding.onCapture = View.OnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_captureActivity)
        }
        binding = databinding
        return databinding.root
    }

}
