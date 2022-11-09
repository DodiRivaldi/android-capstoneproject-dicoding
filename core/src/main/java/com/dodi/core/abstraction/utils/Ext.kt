package com.dodi.core.abstraction.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.showToast(msg : String?){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun androidx.appcompat.widget.AppCompatImageView.load(id: Int) {
    Glide.with(context).asBitmap()
        .load(id)
        .apply(RequestOptions())
        .into(this)
}

fun androidx.appcompat.widget.AppCompatImageView.load(url: Bitmap) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .apply(RequestOptions())
        .into(this)
}

fun androidx.appcompat.widget.AppCompatImageView.load(url: String) {
    Glide.with(context).asBitmap()
        .load(url)
        .fitCenter()
        .apply(RequestOptions())
        .into(this)
}

fun androidx.appcompat.widget.AppCompatImageView.load(url: Uri?) {
    Glide.with(context).asBitmap()
        .load(url)
        .fitCenter()
        .apply(RequestOptions())
        .into(this)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action : (t: T) -> Unit){
    liveData?.observe(this,
        {it?.let{ t -> action(t)}}
    )
}

