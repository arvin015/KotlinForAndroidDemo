package com.arvin.kotlinforandroiddemo

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


enum class BitmapType {
    NORMAL, ROUND, CIRCLE
}

class GlideWrapper {
    var url: String? = null
    var imageView: ImageView? = null
    var loadingPlaceholder: Int = R.mipmap.ic_launcher
    var errorPlaceholder: Int = R.mipmap.ic_launcher
    var bitmapType: BitmapType = BitmapType.NORMAL
    var memoryCache: Boolean = true
}

fun load(init: GlideWrapper.() -> Unit) {
    var wrapper = GlideWrapper()
    wrapper.init()
    work(wrapper)
}

private fun work(wrapper: GlideWrapper) {

    with(wrapper) {
        imageView?.let {

            val mRequestOptions = when (bitmapType) {
                BitmapType.CIRCLE -> getCircleRequestions()
                BitmapType.ROUND -> getRoundCornerRequestions()
                else -> RequestOptions()
            }
            mRequestOptions
                    .placeholder(loadingPlaceholder)
                    .error(errorPlaceholder)
                    .skipMemoryCache(!memoryCache)

            Glide.with(it)
                    .load(wrapper.url)
                    .apply(mRequestOptions)
                    .into(it)
        }
    }
}

private fun getCircleRequestions() = RequestOptions.circleCropTransform()

private fun getRoundCornerRequestions(): RequestOptions {
    val round = RoundedCorners(20)
    return RequestOptions.bitmapTransform(round)
}