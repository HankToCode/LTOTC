package com.lt.ltotc.ui.rv_multi

import androidx.databinding.ObservableField
import com.lt.basics.base.BaseModel
import com.lt.basics.base.BaseViewModel
import com.lt.basics.base.MultiItemViewModel
import com.lt.basics.binding.command.BindingAction
import com.lt.basics.binding.command.BindingCommand
import com.lt.basics.utils.ToastUtils

/**
 * Create Author：HankGreen
 * Create Date：2019/01/25
 * Description：
 */
class MultiRecycleRightItemViewModel(viewModel: MultiRecycleViewModel, text: String) : MultiItemViewModel<BaseViewModel<BaseModel>?>(viewModel as BaseViewModel<BaseModel>) {
    @JvmField
    var text = ObservableField("")
    //条目的点击事件
    @JvmField
    var itemClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            //拿到position
            val position = viewModel.observableList.indexOf(this@MultiRecycleRightItemViewModel)
            ToastUtils.showShort("position：$position")
        }
    })

    init {
        this.text.set(text)
    }
}