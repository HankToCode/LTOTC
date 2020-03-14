package com.lt.basics.http

/**
 * Created by HankGreen on 2017/5/10.
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
class BaseResponse<T> {
    var code = 0
    var message: String? = null
    var result: T? = null
        private set

    fun setResult(result: T) {
        this.result = result
    }

    val isOk: Boolean
        get() = code == 0

}