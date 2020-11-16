package com.app.daggermvvm.extras

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.app.daggermvvm.global.IMAGE_URL

@BindingAdapter("android:setUri")
fun setUri(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(IMAGE_URL + url).into(imageView)
}