package com.lt.ltotc.ui.viewpager.adapter;

import android.view.ViewGroup;

import com.lt.ltotc.databinding.ItemViewpagerBinding;
import com.lt.ltotc.ui.viewpager.vm.ViewPagerItemViewModel;

import androidx.databinding.ViewDataBinding;
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

/**
 * Created by HankGreen on 2018/6/21.
 */

public class ViewPagerBindingAdapter extends BindingViewPagerAdapter<ViewPagerItemViewModel> {

    @Override
    public void onBindBinding(final ViewDataBinding binding, int variableId, int layoutRes, final int position, ViewPagerItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
        ItemViewpagerBinding _binding = (ItemViewpagerBinding) binding;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
