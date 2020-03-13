package com.lt.basics.binding.viewadapter.webview

import android.text.TextUtils
import android.webkit.WebView
import androidx.databinding.BindingAdapter

/**
 * Created by HankGreen on 2017/6/18.
 */
object ViewAdapter {
    @BindingAdapter("render")
    fun loadHtml(webView: WebView, html: String?) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
        }
    }
}