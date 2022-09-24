package com.aibooksearcher.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingViewUtil {

    @BindingAdapter("app:glideCorner")
    @JvmStatic
    fun imageViewGlideCorner(imageView: ImageView, uri: String?) {
        uri?.let {
            imageView.clipToOutline = true
            Glide.with(imageView.context)
                .load(uri)
                .into(imageView)
        } ?: run {
            imageView.setImageResource(android.R.color.transparent)
        }
    }
}