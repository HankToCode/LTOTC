package com.lt.ltotc.ui.viewpager.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.lt.basics.base.BaseActivity
import com.lt.basics.utils.ToastUtils
import com.lt.ltotc.BR
import com.lt.ltotc.R
import com.lt.ltotc.databinding.FragmentViewpagerBinding
import com.lt.ltotc.ui.viewpager.adapter.ViewPagerBindingAdapter
import com.lt.ltotc.ui.viewpager.vm.ViewPagerViewModel

/**
 * ViewPager绑定的例子, 更多绑定方式，请参考 https://github.com/evant/binding-collection-adapter
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by HankGreen on 2018/7/18.
 */
class ViewPagerActivity : BaseActivity<FragmentViewpagerBinding, ViewPagerViewModel>() {
    override fun initContentView(savedInstanceState: Bundle): Int {
        return R.layout.fragment_viewpager
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() { // 使用 TabLayout 和 ViewPager 相关联
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        binding!!.viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding!!.tabs))
        //给ViewPager设置adapter
        binding!!.adapter = ViewPagerBindingAdapter()
    }

    override fun initViewObservable() {
        viewModel!!.itemClickEvent.observe(this, Observer { text -> ToastUtils.showShort("position：$text") })
    }
}