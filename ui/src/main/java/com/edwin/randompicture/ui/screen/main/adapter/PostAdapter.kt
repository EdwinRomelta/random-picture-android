package com.edwin.randompicture.ui.screen.main.adapter

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PostViewBinding
import com.edwin.randompicture.presentation.model.PostView
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.mapper.PostMapper
import javax.inject.Inject


class PostAdapter @Inject constructor(private val fragmentDataBindingComponent: FragmentDataBindingComponent,
                                      private val postMapper: PostMapper) :
        PagedListAdapter<PostView, PostAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PostView>() {
            override fun areItemsTheSame(oldItem: PostView, newItem: PostView): Boolean =
                    oldItem.imgPath == newItem.imgPath

            override fun areContentsTheSame(oldItem: PostView, newItem: PostView): Boolean =
                    oldItem == newItem
        }
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: PostViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.post_view, parent, false, fragmentDataBindingComponent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.binding.postView = postMapper.mapToViewModel(item)
    }

    inner class ViewHolder(val binding: PostViewBinding) : RecyclerView.ViewHolder(binding.root)
}