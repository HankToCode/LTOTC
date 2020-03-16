package com.lt.ltotc.data.source.http

import com.lt.basics.http.BaseResponse
import com.lt.ltotc.data.source.HttpDataSource
import com.lt.ltotc.data.source.http.service.DemoApiService
import com.lt.ltotc.entity.DemoEntity
import com.lt.ltotc.entity.DemoEntity.ItemsEntity
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 处理HTTP请求
 * Created by HankGreen on 2019/3/26.
 */
class HttpDataSourceImpl private constructor(private val apiService: DemoApiService) : HttpDataSource {
    override fun login(): Observable<Any> {
        return Observable.just(Any()).delay(3, TimeUnit.SECONDS) //延迟3秒
    }

    override fun loadMore(): Observable<DemoEntity> {
        return Observable.create<DemoEntity> { observableEmitter ->
            val entity = DemoEntity()
            val itemsEntities: MutableList<ItemsEntity> = ArrayList()
            //模拟一部分假数据
            for (i in 0..9) {
                val item = ItemsEntity()
                item.id = -1
                item.name = "模拟条目"
                itemsEntities.add(item)
            }
            entity.items = itemsEntities
            observableEmitter.onNext(entity)
        }.delay(3, TimeUnit.SECONDS) //延迟3秒
    }

    override fun demoGet(): Observable<BaseResponse<DemoEntity>> {
        return apiService.demoGet()
    }

    override fun demoPost(catalog: String): Observable<BaseResponse<DemoEntity>> {
        return apiService.demoPost(catalog)
    }

    companion object {
        @Volatile
        private var INSTANCE: HttpDataSourceImpl? = null

        fun getInstance(apiService: DemoApiService): HttpDataSourceImpl {
            if (INSTANCE == null) {
                synchronized(HttpDataSourceImpl::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = HttpDataSourceImpl(apiService)
                    }
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}