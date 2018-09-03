package com.arvin.kotlinforandroiddemo

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun ImageView.load(url: String, loadingPlaceholder: Int = R.mipmap.ic_launcher, errorPlaceholder: Int = R.mipmap.ic_launcher, needCache: Boolean = false) {
    val requestOptions = RequestOptions()
    requestOptions.placeholder(loadingPlaceholder)
    requestOptions.error(errorPlaceholder)
    requestOptions.skipMemoryCache(!needCache)

    Glide.with(this)
            .load(url)
            .into(this)
}