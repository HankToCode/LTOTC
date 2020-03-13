package com.lt.basics.binding.viewadapter.spinner

/**
 * Created by HankGreen on 2017/6/18.
 * 下拉Spinner控件的键值对, 实现该接口,返回key,value值, 在xml绑定List<IKeyAndValue>
</IKeyAndValue> */
interface IKeyAndValue {
    val key: String?
    val value: String?
}