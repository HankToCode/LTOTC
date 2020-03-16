package com.lt.ltotc.data.source.http.service

import com.lt.basics.http.BaseResponse
import com.lt.ltotc.entity.DemoEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by HankGreen on 2017/6/15.
 */
interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    fun demoGet(): Observable<BaseResponse<DemoEntity>>

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    fun demoPost(@Field("catalog") catalog: String?): Observable<BaseResponse<DemoEntity>>
}