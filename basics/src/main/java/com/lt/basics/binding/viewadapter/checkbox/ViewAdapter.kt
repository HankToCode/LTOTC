package com.lt.basics.binding.viewadapter.checkbox

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.lt.basics.binding.command.BindingCommand

/**
 * Created by HankGreen on 2017/6/16.
 */
object ViewAdapter {
    /**
     * @param bindingCommand //绑定监听
     */
    @BindingAdapter(value = ["onCheckedChangedCommand"], requireAll = false)
    fun setCheckedChanged(checkBox: CheckBox, bindingCommand: BindingCommand<Boolean?>) {
        checkBox.setOnCheckedChangeListener { compoundButton, b -> bindingCommand.execute(b) }
    }
}