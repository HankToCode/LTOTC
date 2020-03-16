package com.lt.ltotc.binding.twinklingrefreshlayout

import androidx.databinding.BindingAdapter
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lt.basics.binding.command.BindingCommand

/**
 * Created by HankGreen on 2017/6/16.
 * TwinklingRefreshLayout列表刷新的绑定适配器
 */
object ViewAdapter {
    @JvmStatic
    @BindingAdapter(value = ["onRefreshCommand", "onLoadMoreCommand"], requireAll = false)
    fun onRefreshAndLoadMoreCommand(layout: TwinklingRefreshLayout, onRefreshCommand: BindingCommand<*>?, onLoadMoreCommand: BindingCommand<*>?) {
        layout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout) {
                super.onRefresh(refreshLayout)
                onRefreshCommand?.execute()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout) {
                super.onLoadMore(refreshLayout)
                onLoadMoreCommand?.execute()
            }
        })
    }
}