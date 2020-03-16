package com.lt.ltotc.data.source.local

import com.lt.basics.utils.SPUtils
import com.lt.ltotc.data.source.LocalDataSource

/**
 * 本地数据源，可配合Room框架使用
 * Created by HankGreen on 2019/3/26.
 */
class LocalDataSourceImpl private constructor() : LocalDataSource {
    override fun saveUserName(userName: String?) {
        SPUtils.getInstance().put("UserName", userName!!)
    }

    override fun savePassword(password: String?) {
        SPUtils.getInstance().put("password", password!!)
    }

    override fun getUserName(): String? {
        return SPUtils.getInstance().getString("UserName")
    }

    override fun getPassword(): String? {
        return SPUtils.getInstance().getString("password")
    }

    companion object {
        @Volatile
        private var INSTANCE: LocalDataSourceImpl? = null

        val instance: LocalDataSourceImpl?
            get() {
                if (INSTANCE == null) {
                    synchronized(LocalDataSourceImpl::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = LocalDataSourceImpl()
                        }
                    }
                }
                return INSTANCE
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}