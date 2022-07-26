package com.example.infinite_scroll.presenter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.infinite_scroll.R

@BindingAdapter("imageUrl")
fun loadImage(
    imageView: ImageView,
    url: String
) {
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_baseline_image)
        .into(imageView)
}