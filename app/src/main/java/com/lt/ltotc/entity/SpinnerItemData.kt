package com.lt.ltotc.entity

import com.lt.basics.binding.viewadapter.spinner.IKeyAndValue

/**
 * Created by HankGreen on 2017/7/17.
 * Spinner条目数据实体
 * 该实体类可以自定义,比如该类是数据库实体类. 或者是数据字典实体类, 但需要实现IKeyAndValue接口, 返回key和value两个值就可以在Spinner中绑定使用了
 */
class SpinnerItemData(//key是下拉显示的文字
        override val key: String, //value是对应需要上传给后台的值, 这个可以根据具体业务具体定义
        override val value: String) : IKeyAndValue