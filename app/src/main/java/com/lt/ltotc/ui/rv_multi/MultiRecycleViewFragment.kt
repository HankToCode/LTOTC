package com.lt.ltotc.ui.rv_multi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lt.basics.base.BaseFragment
import com.lt.ltotc.BR
import com.lt.ltotc.R
import com.lt.ltotc.databinding.FragmentMultiRvBinding
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：RecycleView多布局实现
 */
class MultiRecycleViewFragment : BaseFragment<FragmentMultiRvBinding?, MultiRecycleViewModel?>() {
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_multi_rv
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象。
// Adapter属于View层的东西, 不建议定义到ViewModel中绑定，以免内存泄漏
        binding!!.adapter = BindingRecyclerViewAdapter<Any?>()
    }
}