package com.arvin.kotlinforandroiddemo

import android.app.AlertDialog
import android.os.Bundle
import com.arvin.kotlinforandroiddemo.entity.LatestNews
import kotlinx.android.synthetic.main.test.*

class TestActivity : BaseActivity<ITestView, TestPresenter>(), ITestView {

    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtn.setOnClickListener {
            finish()
        }

        loadBtn.setOnClickListener {
            presenter?.loadData()
            loadBtn.isEnabled = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getTagName(): String = localClassName

    override fun getLayoutId(): Int = R.layout.test

    override fun createPresenter() = TestPresenter()

    override fun replyData(news: LatestNews) {
        hideLoading()

        contentText.text = news.toString()

        load {
            url = news.stories?.get(0)?.images?.get(0)
            imageView = testImage
        }
    }

    override fun showLoading() {
        dialog = loadDialog(this) {
            message = "加载数据中..."
        }
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }
}