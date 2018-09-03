package com.arvin.kotlinforandroiddemo

import android.app.Activity
import android.os.Bundle

abstract class BaseActivity<V, P : BasePresenter<V>> : Activity() {

    var presenter: P? = null
    lateinit var TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        TAG = getTagName()

        presenter = createPresenter()
        presenter?.attach(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detach()
    }

    abstract fun getTagName(): String

    abstract fun getLayoutId(): Int

    abstract fun createPresenter(): P
}