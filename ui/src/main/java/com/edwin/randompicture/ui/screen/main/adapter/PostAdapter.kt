package com.edwin.randompicture.ui.screen.main.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.presentation.data.NetworkState
import com.edwin.randompicture.presentation.model.PostView
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.mapper.PostMapper
import com.edwin.randompicture.ui.viewholder.NetworkStateViewHolder
import com.edwin.randompicture.ui.viewholder.PostViewHolder
import javax.inject.Inject


class PostAdapter @Inject constructor(private val fragmentDataBindingComponent: FragmentDataBindingComponent,
                                      private val postMapper: PostMapper) :
        PagedListAdapter<PostView, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PostView>() {
            override fun areItemsTheSame(oldItem: PostView, newItem: PostView): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PostView, newItem: PostView): Boolean =
                    oldItem == newItem
        }
    }

    private lateinit var layoutInflater: LayoutInflater
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            R.layout.post_view -> PostViewHolder.create(parent, fragmentDataBindingComponent)
            R.layout.network_state_item -> NetworkStateViewHolder.create(parent, fragmentDataBindingComponent)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.post_view -> {
                val item = getItem(position)
                (holder as PostViewHolder).apply {
                    if (item != null) {
                        bind(postMapper.mapToViewModel(item))
                    } else {
                        bind(null)
                    }
                }
            }
            R.layout.network_state_item -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.post_view
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED
}