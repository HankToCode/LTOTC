package com.lt.ltotc.data.source

import com.lt.basics.http.BaseResponse
import com.lt.ltotc.entity.DemoEntity
import io.reactivex.Observable

/**
 * 提供网络数据源接口定义
 * Created by HankGreen on 2019/3/26.
 */
interface HttpDataSource {
    //模拟登录
    fun login(): Observable<Any>

    //模拟上拉加载
    fun loadMore(): Observable<DemoEntity>

    fun demoGet(): Observable<BaseResponse<DemoEntity>>
    fun demoPost(catalog: String?): Observable<BaseResponse<DemoEntity>>
}