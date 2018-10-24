package com.edwin.randompicture.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject


class FragmentBindingAdapters @Inject constructor(private val fragment: androidx.fragment.app.Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(fragment).load(url).into(imageView)
    }
}