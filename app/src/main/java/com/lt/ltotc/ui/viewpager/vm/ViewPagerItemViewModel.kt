package com.lt.ltotc.ui.viewpager.vm

import com.lt.basics.base.ItemViewModel
import com.lt.basics.binding.command.BindingAction
import com.lt.basics.binding.command.BindingCommand

/**
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by HankGreen on 2018/7/18.
 */
class ViewPagerItemViewModel(viewModel: ViewPagerViewModel, var text: String?) : ItemViewModel<ViewPagerViewModel?>(viewModel) {
    @JvmField
    var onItemClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            //点击之后将逻辑转到activity中处理
            viewModel.itemClickEvent.value = text
        }
    })

}