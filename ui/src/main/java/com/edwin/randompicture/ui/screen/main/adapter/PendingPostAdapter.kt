package com.edwin.randompicture.ui.screen.main.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwin.randompicture.presentation.model.PendingPostView
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.mapper.PendingPostMapper
import com.edwin.randompicture.ui.viewholder.PendingPostViewHolder
import javax.inject.Inject


class PendingPostAdapter @Inject constructor(private val fragmentDataBindingComponent: FragmentDataBindingComponent,
                                             private val pendingPostMapper: PendingPostMapper) :
        PagedListAdapter<PendingPostView, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PendingPostView>() {
            override fun areItemsTheSame(oldItem: PendingPostView, newItem: PendingPostView): Boolean =
                    oldItem.imagePath == newItem.imagePath

            override fun areContentsTheSame(oldItem: PendingPostView, newItem: PendingPostView): Boolean =
                    oldItem == newItem
        }
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return PendingPostViewHolder.create(parent, fragmentDataBindingComponent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as PendingPostViewHolder).apply {
            if (item != null) {
                bind(pendingPostMapper.mapToViewModel(item))
            } else {
                bind(null)
            }
        }
    }
}