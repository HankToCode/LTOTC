package com.lt.basics.http.interceptor

import com.lt.basics.http.download.ProgressResponseBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by HankGreen on 2017/5/10.
 */
class ProgressInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        return originalResponse.newBuilder()
                .body(originalResponse.body()?.let { ProgressResponseBody(it) })
                .build()
    }
}