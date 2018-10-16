package com.edwin.randompicture.ui.viewholder

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.NetworkStateItemBinding
import com.edwin.randompicture.presentation.data.NetworkState

class NetworkStateViewHolder(private val networkStateItemBinding: NetworkStateItemBinding) : RecyclerView.ViewHolder(networkStateItemBinding.root) {

    companion object {
        fun create(parent: ViewGroup, dataBindingComponent: DataBindingComponent): NetworkStateViewHolder {
            val binding: NetworkStateItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.network_state_item, parent, false, dataBindingComponent)
            return NetworkStateViewHolder(binding)
        }
    }

    fun bindTo(networkState: NetworkState?) {
        networkStateItemBinding.networkState = networkState
    }
}