package com.lt.basics.http

import com.lt.basics.http.download.DownLoadSubscriber
import com.lt.basics.http.download.ProgressCallBack
import com.lt.basics.http.interceptor.ProgressInterceptor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

/**
 * Created by HankGreen on 2017/5/11.
 * 文件下载管理，封装一行代码实现下载
 */
class DownLoadManager private constructor() {
    //下载
    fun load(downUrl: String?, callBack: ProgressCallBack<ResponseBody?>) {
        retrofit!!.create(ApiService::class.java)
                .download(downUrl)
                .subscribeOn(Schedulers.io()) //请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext { responseBody -> responseBody?.let { callBack.saveFile(it) } }
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(DownLoadSubscriber(callBack))
    }

    private fun buildNetWork() {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ProgressInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetworkUtil.url)
                .build()
    }

    private interface ApiService {
        @Streaming
        @GET
        fun download(@Url url: String?): Observable<ResponseBody?>
    }

    companion object {
        /**
         * 单例模式
         *
         * @return DownLoadManager
         */
        @JvmStatic
        var instance: DownLoadManager? = null
            get() {
                if (field == null) {
                    field = DownLoadManager()
                }
                return field
            }
            private set
        private var retrofit: Retrofit? = null
    }

    init {
        buildNetWork()
    }
}