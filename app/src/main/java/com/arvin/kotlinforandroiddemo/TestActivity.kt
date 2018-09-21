package com.arvin.kotlinforandroiddemo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import com.arvin.kotlinforandroiddemo.entity.LatestNews
import kotlinx.android.synthetic.main.test.*
import java.util.*

class TestActivity : BaseActivity<ITestView, TestPresenter>(), ITestView {

    var news: LatestNews? = null
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backBtn.setOnClickListener {
            finish()
        }

        loadBtn.setOnClickListener {
            presenter?.loadData()
            loadBtn.isEnabled = false

            bubbleView.startBubbling()

            Thread {
                while (true) {
                    Thread.sleep(200)
                    if (Random().nextInt() > 8) {
                        bulletView.createBullet("hello world")
                    }
                }
            }.start()
        }

        testImage.setOnClickListener {
            startActivity(Intent(this@TestActivity, ContentActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putLong("contentId", news!!.stories!![0]!!.id!!.toLong())
                })
            })
        }


    }

    override fun onPause() {
        super.onPause()
        bubbleView.pauseBubbing()
    }

    override fun onResume() {
        super.onResume()
        bubbleView.resumeBubbing()
    }

    override fun onDestroy() {
        super.onDestroy()
        bubbleView.stopBubbing()
    }

    override fun getTagName(): String = localClassName

    override fun getLayoutId(): Int = R.layout.test

    override fun createPresenter() = TestPresenter()

    override fun replyData(news: LatestNews) {

        this.news = news

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