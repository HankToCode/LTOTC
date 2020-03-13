package com.lt.ltotc.data.source;

import com.lt.ltotc.entity.DemoEntity;

import io.reactivex.Observable;
import com.lt.basics.http.BaseResponse;

/**
 * 提供网络数据源接口定义
 * Created by HankGreen on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);


}
