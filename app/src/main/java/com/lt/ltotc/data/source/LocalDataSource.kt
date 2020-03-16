package com.lt.ltotc.data.source

/**
 * Created by HankGreen on 2019/3/26.
 */
interface LocalDataSource {
    /**
     * 保存用户名
     */
    fun saveUserName(userName: String?)

    /**
     * 保存用户密码
     */
    fun savePassword(password: String?)

    /**
     * 获取用户名
     */
    fun getUserName(): String?

    /**
     * 获取用户密码
     */
    fun getPassword(): String?
}