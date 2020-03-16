package com.lt.ltotc.ui.rv_multi;

import androidx.annotation.NonNull;
import com.lt.basics.base.BaseViewModel;
import com.lt.basics.base.MultiItemViewModel;
import com.lt.basics.binding.command.BindingAction;
import com.lt.basics.binding.command.BindingCommand;
import com.lt.basics.utils.ToastUtils;

/**
 * Create Author：HankGreen
 * Create Date：2019/01/25
 * Description：
 */

public class MultiRecycleHeadViewModel extends MultiItemViewModel {

    public MultiRecycleHeadViewModel(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
