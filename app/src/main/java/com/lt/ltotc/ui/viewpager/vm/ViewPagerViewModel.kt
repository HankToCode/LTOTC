package com.lt.ltotc.ui.viewpager.vm

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.lt.basics.base.BaseModel
import com.lt.basics.base.BaseViewModel
import com.lt.basics.binding.command.BindingCommand
import com.lt.basics.binding.command.BindingConsumer
import com.lt.basics.bus.event.SingleLiveEvent
import com.lt.basics.utils.ToastUtils
import com.lt.ltotc.BR
import com.lt.ltotc.R
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter.PageTitles
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by HankGreen on 2018/7/18.
 */
open class ViewPagerViewModel(application: Application) : BaseViewModel<BaseModel?>(application) {
    @JvmField
    var itemClickEvent = SingleLiveEvent<String>()
    //给ViewPager添加ObservableList
    @JvmField
    var items: ObservableList<ViewPagerItemViewModel> = ObservableArrayList()
    //给ViewPager添加ItemBinding
    @JvmField
    var itemBinding = ItemBinding.of<ViewPagerItemViewModel>(BR.viewModel, R.layout.item_viewpager)
    //给ViewPager添加PageTitle
    @JvmField
    val pageTitles: PageTitles<ViewPagerItemViewModel?> = PageTitles<ViewPagerItemViewModel?> { position, item -> "条目$position" }
    //ViewPager切换监听
    @JvmField
    var onPageSelectedCommand = BindingCommand(object : BindingConsumer<Int?> {

        override fun call(index: Int?) {
            ToastUtils.showShort("ViewPager切换：$index")
        }
    })

    init {
        //模拟3个ViewPager页面
        for (i in 1..3) {
            val itemViewModel = ViewPagerItemViewModel(this, "第" + i + "个页面")
            items.add(itemViewModel)
        }
    }
}