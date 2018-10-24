package com.edwin.randompicture.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PendingPostViewBinding
import com.edwin.randompicture.ui.model.PendingPostViewModel

class PendingPostViewHolder(private val pendingPostViewBinding: PendingPostViewBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(pendingPostViewBinding.root) {

    companion object {
        fun create(parent: ViewGroup, dataBindingComponent: DataBindingComponent): PendingPostViewHolder {
            val binding: PendingPostViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.pending_post_view, parent, false, dataBindingComponent)
            return PendingPostViewHolder(binding)
        }
    }

    fun bind(pendingPostViewModel: PendingPostViewModel?) {
        pendingPostViewBinding.pendingPost = pendingPostViewModel
    }
}