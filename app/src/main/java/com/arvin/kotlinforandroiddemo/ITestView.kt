package com.arvin.kotlinforandroiddemo

import com.arvin.kotlinforandroiddemo.entity.LatestNews

interface ITestView : IBaseView {

    fun replyData(news: LatestNews)
}