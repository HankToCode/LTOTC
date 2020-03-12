package com.lt.ltotc.ui.tab_bar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lt.ltotc.BR;
import com.lt.ltotc.R;

import androidx.annotation.Nullable;
import com.lt.basics.base.BaseFragment;

/**
 * Created by HankGreen on 2018/7/18.
 */

public class TabBar2Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_2;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

}
