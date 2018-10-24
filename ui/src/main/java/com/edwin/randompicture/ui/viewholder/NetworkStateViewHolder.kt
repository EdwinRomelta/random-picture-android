package com.edwin.randompicture.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.NetworkStateItemBinding
import com.edwin.randompicture.presentation.data.NetworkState

class NetworkStateViewHolder(private val networkStateItemBinding: NetworkStateItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(networkStateItemBinding.root) {

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