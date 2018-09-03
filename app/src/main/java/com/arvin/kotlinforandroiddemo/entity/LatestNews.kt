package com.arvin.kotlinforandroiddemo.entity

data class LatestNews(var date: String? = null,
                      var stories: List<StoriesBean>? = null,
                      var topStories: List<TopStoriesBean>? = null)
