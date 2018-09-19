package com.arvin.kotlinforandroiddemo

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.content.*

class ContentActivity : BaseActivity<IContentView, ContentPresenter>(), IContentView {

    private var contentId: Long = 0
    private lateinit var webChromeClient: MyWebChromeClient
    private lateinit var webViewClient: MyWebViewClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentId = intent.extras.getLong("contentId")

        backBtn.setOnClickListener {
            finish()
        }

        refreshBtn.setOnClickListener {
            webView.reload()
        }

        webViewClient = MyWebViewClient()
        webViewClient.listener = object : MyWebViewClient.IMyWebViewClient {
            override fun onPageStarted() {

            }

            override fun onPageFinished() {
                loadingBar.visibility = View.GONE
            }
        }
        webView.webViewClient = webViewClient

        webChromeClient = MyWebChromeClient()
        webChromeClient.listener = object : MyWebChromeClient.IMyWebChromeClient {
            override fun onProgressChanged(newProgress: Int) {
                loadingBar.progress = newProgress
            }
        }
        webView.webChromeClient = webChromeClient

        webView.loadUrl("https://news-at.zhihu.com/story/$contentId")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getTagName(): String = localClassName

    override fun getLayoutId(): Int = R.layout.content

    override fun createPresenter() = ContentPresenter()

    override fun showLoading() {
        loadDialog(this) {
            message = "加载数据中..."
        }.show()
    }

    override fun hideLoading() {

    }

}