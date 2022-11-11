package com.dodi.core.abstraction.utils

import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dodi.core.R

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
  imageUrl?.let {
    Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(imageView)
  }
}

@BindingAdapter("app:isFavorite")
fun setIsFavorite(checkBox: CheckBox, isFavorite: Boolean) {
  checkBox.isChecked = isFavorite
}