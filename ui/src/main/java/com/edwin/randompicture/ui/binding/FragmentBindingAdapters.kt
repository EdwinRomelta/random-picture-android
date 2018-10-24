package com.edwin.randompicture.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.edwin.randompicture.ui.thirdparty.glide.GlideApp
import javax.inject.Inject


class FragmentBindingAdapters @Inject constructor(private val fragment: Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        GlideApp.with(fragment).load(url).centerCrop().into(imageView)
    }
}