package com.arvin.kotlinforandroiddemo

open class BasePresenter<V> {

    var viewListener: V? = null

    fun attach(viewListener: V) {
        this.viewListener = viewListener
    }

    fun detach() {
        viewListener = null
    }
}