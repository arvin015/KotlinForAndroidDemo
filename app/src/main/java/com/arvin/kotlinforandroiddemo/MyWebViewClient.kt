package com.arvin.kotlinforandroiddemo

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {

    var listener: IMyWebViewClient? = null

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        listener?.onPageStarted()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        listener?.onPageFinished()
    }

    interface IMyWebViewClient {
        fun onPageStarted()
        fun onPageFinished()
    }
}