package com.lt.basics.binding.viewadapter.viewgroup

import androidx.databinding.ViewDataBinding

/**
 * Created by HankGreen on 2017/6/15.
 */
interface IBindingItemViewModel<V : ViewDataBinding?> {
    fun injecDataBinding(binding: V)
}