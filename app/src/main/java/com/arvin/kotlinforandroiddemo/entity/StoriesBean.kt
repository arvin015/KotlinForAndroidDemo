package com.arvin.kotlinforandroiddemo.entity

/**
 * title : 中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）
 * ga_prefix : 052321
 * images : ["http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"]
 * type : 0
 * id : 3930445
 */

data class StoriesBean(var title: String? = null,
                       var ga_prefix: String? = null,
                       var type: Int = 0,
                       var id: Int = 0,
                       var images: List<String>? = null)
