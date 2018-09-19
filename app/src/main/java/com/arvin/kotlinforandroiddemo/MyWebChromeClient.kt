package com.arvin.kotlinforandroiddemo

import android.webkit.WebChromeClient
import android.webkit.WebView

class MyWebChromeClient : WebChromeClient() {

    var listener: IMyWebChromeClient? = null

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)

        listener?.onProgressChanged(newProgress)
    }

    interface IMyWebChromeClient {
        fun onProgressChanged(newProgress: Int)
    }
}