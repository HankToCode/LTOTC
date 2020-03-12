package com.lt.ltotc.ui.network.detail;

import android.app.Application;

import com.lt.ltotc.entity.DemoEntity;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.lt.basics.base.BaseViewModel;

/**
 * Created by HankGreen on 2017/7/17.
 */

public class DetailViewModel extends BaseViewModel {
    public ObservableField<DemoEntity.ItemsEntity> entity = new ObservableField<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setDemoEntity(DemoEntity.ItemsEntity entity) {
        this.entity.set(entity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        entity = null;
    }
}
