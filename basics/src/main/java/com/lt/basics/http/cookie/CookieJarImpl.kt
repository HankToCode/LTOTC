package com.lt.basics.http.cookie

import com.lt.basics.http.cookie.store.CookieStore
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by HankGreen on 2017/5/13.
 */
class CookieJarImpl(cookieStore: CookieStore?) : CookieJar {
    val cookieStore: CookieStore
    @Synchronized
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.saveCookie(url, cookies)
    }

    @Synchronized
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore.loadCookie(url)
    }

    init {
        requireNotNull(cookieStore) { "cookieStore can not be null!" }
        this.cookieStore = cookieStore
    }
}