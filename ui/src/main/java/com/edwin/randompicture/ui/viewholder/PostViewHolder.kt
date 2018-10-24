package com.edwin.randompicture.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PostViewBinding
import com.edwin.randompicture.ui.model.PostViewModel

class PostViewHolder(private val postViewBinding: PostViewBinding,
                     private val postItemDelegate: PostItemDelegate) : RecyclerView.ViewHolder(postViewBinding.root) {

    companion object {
        fun create(parent: ViewGroup, dataBindingComponent: DataBindingComponent, postItemDelegate: PostItemDelegate): PostViewHolder {
            val binding: PostViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.post_view, parent, false, dataBindingComponent)
            return PostViewHolder(binding, postItemDelegate)
        }
    }

    init {
        postViewBinding.apply {
            photoImageView.setOnClickListener {
                postView?.let { postItemDelegate.onItemClick(it) }
            }
        }
    }

    fun bind(post: PostViewModel?) {
        postViewBinding.postView = post
    }

    interface PostItemDelegate {

        fun onItemClick(postViewModel: PostViewModel)
    }
}