package com.example.infinite_scroll.view.adapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.infinite_scroll.R

@BindingAdapter("imageUrl")
fun loadImage(
    imageView: ImageView,
    url: String
) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(ContextCompat.getDrawable(imageView.context, R.drawable.ic_baseline_image))
        .into(imageView)
}