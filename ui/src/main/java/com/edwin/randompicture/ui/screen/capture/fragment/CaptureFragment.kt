package com.edwin.randompicture.ui.screen.capture.fragment

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.CaptureFragmentBinding
import com.edwin.randompicture.presentation.data.ResourceState
import com.edwin.randompicture.presentation.viewmodel.PhotoViewModel
import com.edwin.randompicture.presentation.viewmodel.PhotoViewModelFactory
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.mapper.PhotoMapper
import com.edwin.randompicture.ui.util.autoCleared
import com.jakewharton.rxbinding2.view.clicks
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class CaptureFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = CaptureFragment()
    }

    var binding by autoCleared<CaptureFragmentBinding>()

    @Inject
    lateinit var viewModelFactory: PhotoViewModelFactory
    @Inject
    lateinit var mapper: PhotoMapper
    @Inject
    lateinit var rxPermissions: RxPermissions
    private lateinit var photoViewModel: PhotoViewModel

    override fun onCreateView(onCreateDisposable: CompositeDisposable,
                              inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: CaptureFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.capture_fragment, container, false)
        binding = databinding
        onCreateDisposable.add(binding.captureButton.clicks()
                .subscribe {
                    binding.cameraView.captureImage {
                        photoViewModel.createPhoto(it.jpeg)
                    }
                }
        )
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photoViewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)
        photoViewModel.photo.observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    if (it.data?.filePath != null) {
                        toast("CAPTURED")
                    }
                }
                ResourceState.LOADING -> {
                }
                ResourceState.ERROR -> {
                    it.message?.let { toast(it) }
                }
            }
        })
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        onStartDisposable.add(rxPermissions.requestEachCombined(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .subscribe { permission ->
                    when {
                        permission.granted -> {
                            binding.cameraView.start()
                        }
                        permission.shouldShowRequestPermissionRationale -> {
                            alert(R.string.capturescreen_permissionrationale, R.string.all_appname) {
                                positiveButton(R.string.all_ok) {
                                    it.dismiss()
                                }
                            }.show()
                        }
                        else -> {
                            alert(R.string.capturescreen_permissiondeny, R.string.all_appname) {
                                positiveButton(R.string.permissionalert_opensetting) {
                                    context?.let {
                                        val intent = Intent()
                                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                        val uri = Uri.fromParts("package", it.packageName, null)
                                        intent.data = uri
                                        if (intent.resolveActivity(it.packageManager) != null) {
                                            startActivity(intent)
                                        } else {
                                            toast(R.string.permissiondialog_nosettingintent)
                                        }
                                    }
                                }
                            }.show()
                        }
                    }
                }
        )
    }

    override fun onStop() {
        binding.cameraView.stop()
        super.onStop()
    }
}
