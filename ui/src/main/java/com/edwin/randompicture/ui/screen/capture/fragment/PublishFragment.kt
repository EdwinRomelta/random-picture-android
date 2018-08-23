package com.edwin.randompicture.ui.screen.capture.fragment


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PublishFragmentBinding
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.util.autoCleared
import javax.inject.Inject

class PublishFragment : Fragment(), Injectable {

    @Inject
    lateinit var fragmentDataBindingComponent: FragmentDataBindingComponent
    private lateinit var filePath: String

    var binding by autoCleared<PublishFragmentBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filePath = PublishFragmentArgs.fromBundle(arguments).filePath
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: PublishFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.publish_fragment, container, false, fragmentDataBindingComponent)
        databinding.filePath = filePath
        binding = databinding
        return binding.root
    }
}
