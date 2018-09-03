package com.arvin.kotlinforandroiddemo

import android.app.AlertDialog
import android.content.Context

class LoadingWrapper {
    var title: String = ""
    var message: String = ""
}

fun loadDialog(context: Context, init: LoadingWrapper.() -> Unit): AlertDialog {

    val loadingWrapper = LoadingWrapper()
    loadingWrapper.init()

    return AlertDialog.Builder(context)
            .setTitle(loadingWrapper.title)
            .setMessage(loadingWrapper.message)
            .create()
}