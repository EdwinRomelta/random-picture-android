package com.edwin.randompicture.ui.screen.main.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.MainFragmentBinding
import com.edwin.randompicture.presentation.data.NetworkState
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostViewModelFactory
import com.edwin.randompicture.presentation.viewmodel.pendingpost.PendingPostsViewModel
import com.edwin.randompicture.presentation.viewmodel.post.PostViewModelFactory
import com.edwin.randompicture.presentation.viewmodel.post.PostsViewModel
import com.edwin.randompicture.ui.base.BaseFragment
import com.edwin.randompicture.ui.di.Injectable
import com.edwin.randompicture.ui.screen.main.adapter.PendingPostAdapter
import com.edwin.randompicture.ui.screen.main.adapter.PostAdapter
import com.edwin.randompicture.ui.util.autoCleared
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_fragment.*
import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter
import org.jetbrains.anko.support.v4.onRefresh
import javax.inject.Inject

class MainFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var pendingPostViewModelFactory: PendingPostViewModelFactory
    @Inject
    lateinit var viewModelFactory: PostViewModelFactory
    @Inject
    lateinit var pendingPostAdapter: PendingPostAdapter
    @Inject
    lateinit var postAdapter: PostAdapter

    private val mainAdapter = RecyclerViewMergeAdapter()
    private lateinit var pendingPostsViewModel: PendingPostsViewModel
    private lateinit var postsViewModel: PostsViewModel

    var binding by autoCleared<MainFragmentBinding>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pendingPostsViewModel = ViewModelProviders.of(this, pendingPostViewModelFactory).get(PendingPostsViewModel::class.java)
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
            mainAdapter.addAdapter(pendingPostAdapter)
            mainAdapter.addAdapter(postAdapter)
            adapter = mainAdapter
            swipeRefreshLayout.onRefresh {
                postsViewModel.refresh()
            }
        }
    }

    override fun onStart(onStartDisposable: CompositeDisposable) {
        super.onStart(onStartDisposable)
        pendingPostsViewModel.pendingPost.observe(this, Observer {
            binding.apply {
                pendingPostAdapter.submitList(it)
            }
        })
        postsViewModel.posts.observe(this, Observer {
            binding.apply {
                postAdapter.submitList(it)
            }
            postsViewModel.networkState.observe(this, Observer { networkState ->
                postAdapter.setNetworkState(networkState)
            })
            postsViewModel.refreshState.observe(this, Observer { networkState ->
                swipeRefreshLayout.isRefreshing = networkState == NetworkState.LOADING
            })
        })
    }
}
