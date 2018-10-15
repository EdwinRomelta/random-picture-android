package com.edwin.randompicture.ui.screen.main.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.MainFragmentBinding
import com.edwin.randompicture.presentation.viewmodel.post.PostViewModelFactory
import com.edwin.randompicture.presentation.viewmodel.post.PostsViewModel
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.screen.main.adapter.PostAdapter
import com.edwin.randompicture.ui.util.autoCleared
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: PostViewModelFactory
    @Inject
    lateinit var postAdapter: PostAdapter

    private lateinit var postsViewModel: PostsViewModel

    var binding by autoCleared<MainFragmentBinding>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)
    }

    override fun onCreateView(onCreateDisposable: CompositeDisposable, inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val databinding: MainFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        databinding.onCapture = View.OnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_captureActivity)
        }
        binding = databinding
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.postRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            adapter = postAdapter
        }
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart(onStartDisposable)
        postsViewModel.posts.observe(this, Observer {
            binding.apply {
                Log.d("EDWIN", "List size ${it?.size ?: 0}")
                postRecycleView.visibility = View.VISIBLE
                if (0 == it?.size ?: 0) {
                    loadingProgressBar.visibility = View.VISIBLE
                } else {
                    loadingProgressBar.visibility = View.GONE
                }
                postAdapter.submitList(it)
            }
//                when (it?.status) {
//                    ResourceState.LOADING -> {
//                        loadingProgressBar.visibility = View.VISIBLE
//                        postRecycleView.visibility = View.GONE
//                    }
//                    ResourceState.SUCCESS -> {
//                        loadingProgressBar.visibility = View.GONE
//                        it.data?.let { postViewList ->
//                            postAdapter.posts = postViewList.map { postView ->
//                                postMapper.mapToViewModel(postView)
//                            }
//                            postAdapter.notifyDataSetChanged()
//                        }
//                        postRecycleView.visibility = View.VISIBLE
//                    }
//                    ResourceState.ERROR -> {
//                        loadingProgressBar.visibility = View.GONE
//                        postRecycleView.visibility = View.VISIBLE
//                    }
//                }
//            }
        })
    }
}
