package com.lt.ltotc.ui.rv_multi

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.lt.basics.base.BaseModel
import com.lt.basics.base.BaseViewModel
import com.lt.basics.base.MultiItemViewModel
import com.lt.ltotc.BR
import com.lt.ltotc.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Create Author：HankGreen
 * Create Date：2019/01/25
 * Description：
 */
class MultiRecycleViewModel(application: Application) : BaseViewModel<BaseModel?>(application) {
    //给RecyclerView添加ObservableList
    @JvmField
    var observableList: ObservableList<MultiItemViewModel<*>> = ObservableArrayList()
    //RecyclerView多布局添加ItemBinding
    @JvmField
    var itemBinding = ItemBinding.of<MultiItemViewModel<*>> { itemBinding, _, item ->
        //通过item的类型, 动态设置Item加载的布局
        val itemType = item.itemType as String?
        if (MultiRecycleType_Head == itemType) { //设置头布局
            itemBinding[BR.viewModel] = R.layout.item_multi_head
        } else if (MultiRecycleType_Left == itemType) { //设置左布局
            itemBinding[BR.viewModel] = R.layout.item_multi_rv_left
        } else if (MultiRecycleType_Right == itemType) { //设置右布局
            itemBinding[BR.viewModel] = R.layout.item_multi_rv_right
        }
    }

    companion object {
        private const val MultiRecycleType_Head = "head"
        private const val MultiRecycleType_Left = "left"
        private const val MultiRecycleType_Right = "right"
    }

    init {
        //模拟10个条目，数据源可以来自网络
        for (i in 0..19) {
            if (i == 0) {
                val item: MultiItemViewModel<*> = MultiRecycleHeadViewModel(this)
                //条目类型为头布局
                item.multiItemType(MultiRecycleType_Head)
                observableList.add(item)
            } else {
                val text = "我是第" + i + "条"
                if (i % 2 == 0) {
                    val item: MultiItemViewModel<*> = MultiRecycleLeftItemViewModel(this, text)
                    //条目类型为左布局
                    item.multiItemType(MultiRecycleType_Left)
                    observableList.add(item)
                } else {
                    val item: MultiItemViewModel<*> = MultiRecycleRightItemViewModel(this, text)
                    //条目类型为右布局
                    item.multiItemType(MultiRecycleType_Right)
                    observableList.add(item)
                }
            }
        }
    }
}