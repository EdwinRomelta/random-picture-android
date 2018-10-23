package com.edwin.randompicture.ui.screen.capture.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PublishFragmentBinding
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.data.error.ErrorDialogResource
import com.edwin.randompicture.presentation.data.error.ValidationErrorResource
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostViewModel
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostViewModelFactory
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.mapper.PreviewPendingPostMapper
import com.edwin.randompicture.ui.util.autoCleared
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PublishFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: PendingPostViewModelFactory
    @Inject
    lateinit var fragmentDataBindingComponent: FragmentDataBindingComponent
    @Inject
    lateinit var previewPendingPostMapper: PreviewPendingPostMapper
    private lateinit var pendingPostViewModel: PendingPostViewModel

    var binding by autoCleared<PublishFragmentBinding>()

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding: PublishFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.publish_fragment, container, false, fragmentDataBindingComponent)
        binding = databinding
        onCreateDisposable.add(
                binding.publishButton.clicks()
                        .subscribe {
                            databinding.previewPendingPost?.apply {
                                pendingPostViewModel.submitPendingPost(imagePath, caption)
                            }
                        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pendingPostViewModel = ViewModelProviders.of(this, viewModelFactory).get(PendingPostViewModel::class.java)

        val filePath = PublishFragmentArgs.fromBundle(arguments).filePath
        pendingPostViewModel.createPendingPost(filePath)
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart(onStartDisposable)

        pendingPostViewModel.pendingPost.observe(this, Observer {
            it?.apply { binding.previewPendingPost = previewPendingPostMapper.mapToViewModel(this) }
        })

        pendingPostViewModel.uploadPendingPost.observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    activity?.finish()
                }
                ResourceState.LOADING -> {
                    binding.captionTextInputLayout.error = null
                }
                ResourceState.ERROR -> {
                    it.errorResource?.let { errorResource ->
                        when (errorResource) {
                            is ValidationErrorResource -> {
                                if (errorResource.errorCode == PendingPostViewModel.EMPTY_CAPTION) {
                                    binding.captionTextInputLayout.error = getString(R.string.publishscreen_erroremptycaption)
                                } else {

                                }
                            }
                            is ErrorDialogResource -> context?.apply { errorResource.show(this) }
                            else -> {

                            }
                        }
                    }
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        pendingPostViewModel.uploadPendingPost.removeObservers(this)
    }

}
