package com.lt.ltotc.ui.base.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.lt.basics.base.BaseModel
import com.lt.basics.base.BaseViewModel
import com.lt.basics.binding.command.BindingAction
import com.lt.basics.binding.command.BindingCommand

/**
 * Create Author：HankGreen
 * Create Date：2019/01/03
 * Description： 对应include标题的ToolbarViewModel
 * Toolbar的封装方式有很多种，具体封装需根据项目实际业务和习惯来编写
 * 所有例子仅做参考,业务多种多样,可能我这里写的例子和你的需求不同，理解如何使用才最重要。
 */
open class ToolbarViewModel<M : BaseModel?> @JvmOverloads constructor(application: Application, model: M? = null) : BaseViewModel<M>(application, model) {
    //标题文字
    @JvmField
    var titleText = ObservableField("")
    //右边文字
    @JvmField
    var rightText = ObservableField("更多")
    //右边文字的观察者
    @JvmField
    var rightTextVisibleObservable = ObservableInt(View.GONE)
    //右边图标的观察者
    @JvmField
    var rightIconVisibleObservable = ObservableInt(View.GONE)
    //兼容databinding，去泛型化
    @JvmField
    var toolbarViewModel: ToolbarViewModel<*>

    /**
     * 设置标题
     *
     * @param text 标题文字
     */
    fun setTitleText(text: String) {
        titleText.set(text)
    }

    /**
     * 设置右边文字
     *
     * @param text 右边文字
     */
    fun setRightText(text: String) {
        rightText.set(text)
    }

    /**
     * 设置右边文字的显示和隐藏
     *
     * @param visibility
     */
    fun setRightTextVisible(visibility: Int) {
        rightTextVisibleObservable.set(visibility)
    }

    /**
     * 设置右边图标的显示和隐藏
     *
     * @param visibility
     */
    fun setRightIconVisible(visibility: Int) {
        rightIconVisibleObservable.set(visibility)
    }

    /**
     * 返回按钮的点击事件
     */
    @JvmField
    val backOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            finish()
        }
    })
    var rightTextOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            rightTextOnClick()
        }
    })
    var rightIconOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            rightIconOnClick()
        }
    })

    /**
     * 右边文字的点击事件，子类可重写
     */
    protected open fun rightTextOnClick() {}

    /**
     * 右边图标的点击事件，子类可重写
     */
    protected fun rightIconOnClick() {}

    init {
        toolbarViewModel = this
    }
}