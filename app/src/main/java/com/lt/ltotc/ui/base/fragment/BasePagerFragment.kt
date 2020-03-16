package com.lt.ltotc.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.lt.basics.base.BaseFragment
import com.lt.basics.base.BaseViewModel
import com.lt.ltotc.BR
import com.lt.ltotc.R
import com.lt.ltotc.databinding.FragmentBasePagerBinding
import com.lt.ltotc.ui.base.adapter.BaseFragmentPagerAdapter

/**
 * Created by HankGreen on 2017/7/17.
 * 抽取的二级BasePagerFragment
 */
abstract class BasePagerFragment : BaseFragment<FragmentBasePagerBinding?, BaseViewModel<*>?>() {
    private var mFragments: List<Fragment>? = null
    private var titlePager: List<String>? = null
    protected abstract fun pagerFragment(): List<Fragment>?
    protected abstract fun pagerTitleString(): List<String>?
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_base_pager
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        mFragments = pagerFragment()
        titlePager = pagerTitleString()
        //设置Adapter
        val pagerAdapter = BaseFragmentPagerAdapter(childFragmentManager, mFragments!!, titlePager!!)
        binding!!.viewPager.adapter = pagerAdapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        binding!!.viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding!!.tabs))
    }

    override fun initViewObservable() {}
}