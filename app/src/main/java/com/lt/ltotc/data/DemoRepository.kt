package com.lt.ltotc.data

import androidx.annotation.VisibleForTesting
import com.lt.basics.base.BaseModel
import com.lt.basics.http.BaseResponse
import com.lt.ltotc.data.source.HttpDataSource
import com.lt.ltotc.data.source.LocalDataSource
import com.lt.ltotc.entity.DemoEntity
import io.reactivex.Observable

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repository）
 * Created by HankGreen on 2019/3/26.
 */
class DemoRepository private constructor(private val mHttpDataSource: HttpDataSource,
                                         private val mLocalDataSource: LocalDataSource) : BaseModel(), HttpDataSource, LocalDataSource {
    override fun login(): Observable<Any> {
        return mHttpDataSource.login()
    }

    override fun loadMore(): Observable<DemoEntity> {
        return mHttpDataSource.loadMore()
    }

    override fun demoGet(): Observable<BaseResponse<DemoEntity>> {
        return mHttpDataSource.demoGet()
    }

    override fun demoPost(catalog: String): Observable<BaseResponse<DemoEntity>> {
        return mHttpDataSource.demoPost(catalog)
    }

    override fun saveUserName(userName: String) {
        mLocalDataSource.saveUserName(userName)
    }

    override fun savePassword(password: String) {
        mLocalDataSource.savePassword(password)
    }

    override fun getUserName(): String? {
        return mLocalDataSource.getUserName()
    }

    override fun getPassword(): String? {
        return mLocalDataSource.getPassword()
    }

    companion object {
        @Volatile
        private var INSTANCE: DemoRepository? = null

        fun getInstance(httpDataSource: HttpDataSource,
                        localDataSource: LocalDataSource): DemoRepository {
            if (INSTANCE == null) {
                synchronized(DemoRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DemoRepository(httpDataSource, localDataSource)
                    }
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}