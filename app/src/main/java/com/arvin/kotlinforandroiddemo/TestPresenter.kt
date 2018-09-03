package com.arvin.kotlinforandroiddemo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestPresenter : BasePresenter<ITestView>() {

    private val url = "http://news-at.zhihu.com/"

    private var newsService = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TestApi::class.java)

    fun loadData() {

        viewListener?.showLoading()

        newsService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewListener?.replyData(it)
                }
    }
}