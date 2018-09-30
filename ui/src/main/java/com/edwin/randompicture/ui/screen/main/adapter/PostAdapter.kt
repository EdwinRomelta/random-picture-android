package com.edwin.randompicture.ui.screen.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PostViewBinding
import com.edwin.randompicture.ui.binding.FragmentDataBindingComponent
import com.edwin.randompicture.ui.model.PostViewModel
import javax.inject.Inject


class PostAdapter @Inject constructor(private val fragmentDataBindingComponent: FragmentDataBindingComponent) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    var posts: List<PostViewModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: PostViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.post_view, parent, false, fragmentDataBindingComponent)
        return ViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.postView = posts[position]
    }

    inner class ViewHolder(val binding: PostViewBinding) : RecyclerView.ViewHolder(binding.root)
}