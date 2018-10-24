package com.edwin.randompicture.ui.screen.preview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.edwin.randompicture.R
import com.edwin.randompicture.databinding.PreviewActivityBinding

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<PreviewActivityBinding>(this, R.layout.preview_activity)

        Glide.with(this).load(PreviewActivityArgs.fromBundle(intent.extras).imgUri).into(binding.previewImageView)
    }
}