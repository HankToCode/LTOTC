package com.lt.basics.binding.viewadapter.viewgroup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by HankGreen on 2017/6/18.
 */
object ViewAdapter {
    @BindingAdapter("itemView", "observableList")
    fun addViews(viewGroup: ViewGroup, itemBinding: ItemBinding<*>, viewModelList: ObservableList<IBindingItemViewModel<ViewDataBinding>>?) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews()
            for (viewModel in viewModelList) {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(viewGroup.context),
                        itemBinding.layoutRes(), viewGroup, true)
                binding.setVariable(itemBinding.variableId(), viewModel)
                viewModel.injecDataBinding(binding)
            }
        }
    }
}