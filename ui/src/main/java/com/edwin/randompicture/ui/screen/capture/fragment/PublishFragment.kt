package com.edwin.randompicture.ui.screen.capture.fragment


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PublishFragmentBinding
import com.edwin.randompicture.presentation.data.Resource
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostViewModel
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostViewModelFactory
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.util.autoCleared
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class PublishFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: PendingPostViewModelFactory
    @Inject
    lateinit var fragmentDataBindingComponent: FragmentDataBindingComponent
    private lateinit var filePath: String
    private lateinit var pendingPostViewModel: PendingPostViewModel

    private var publishLiveData: LiveData<Resource<Unit>>? = null

    var binding by autoCleared<PublishFragmentBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filePath = PublishFragmentArgs.fromBundle(arguments).filePath
    }

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding: PublishFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.publish_fragment, container, false, fragmentDataBindingComponent)
        databinding.filePath = filePath
        binding = databinding
        onCreateDisposable.add(binding.publishButton.clicks()
                .subscribe {
                    publishLiveData = pendingPostViewModel.createPendingPost(filePath, databinding.captionEditText.toString())
                    publishLiveData?.let { liveData ->
                        if (liveData.hasActiveObservers())
                            liveData.removeObservers(this)
                        liveData.observe(this, Observer {
                            when (it?.status) {
                                ResourceState.SUCCESS -> {
                                    activity?.finish()
                                }
                                ResourceState.LOADING -> {
                                }
                                ResourceState.ERROR -> {
                                    it.message?.let { toast(it) }
                                }
                            }
                        })
                    }
                }
        )
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pendingPostViewModel = ViewModelProviders.of(this, viewModelFactory).get(PendingPostViewModel::class.java)
    }


    override fun onPause() {
        publishLiveData?.let {
            if (it.hasActiveObservers()) {
                it.removeObservers(this)
            }
        }
        super.onPause()
    }
}
