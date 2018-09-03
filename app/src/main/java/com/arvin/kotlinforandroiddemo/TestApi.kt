package com.arvin.kotlinforandroiddemo

import com.arvin.kotlinforandroiddemo.entity.LatestNews
import io.reactivex.Observable
import retrofit2.http.GET

interface TestApi {

    @GET("api/4/news/latest")
    fun getNews(): Observable<LatestNews>
}