package com.lt.ltotc.app

import com.lt.basics.base.BaseApplication
import com.lt.basics.crash.CaocConfig
import com.lt.basics.crash.CaocConfig.Builder.Companion.create
import com.lt.basics.utils.KLog
import com.lt.ltotc.BuildConfig
import com.lt.ltotc.R
import com.lt.ltotc.ui.login.LoginActivity
import com.squareup.leakcanary.LeakCanary

/**
 * Created by HankGreen on 2017/7/16.
 */
class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG)
        //初始化全局异常崩溃
        initCrash()
        //内存泄漏检测
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }

    private fun initCrash() {
        create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginActivity::class.java) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply()
    }
}