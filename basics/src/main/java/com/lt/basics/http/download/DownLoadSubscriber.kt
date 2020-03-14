package com.lt.basics.http.download

import io.reactivex.observers.DisposableObserver

/**
 * Created by HankGreen on 2017/5/11.
 */
class DownLoadSubscriber<T>(private val fileCallBack: ProgressCallBack<T>?) : DisposableObserver<T>() {
    public override fun onStart() {
        super.onStart()
        fileCallBack?.onStart()
    }

    override fun onComplete() {
        fileCallBack?.onCompleted()
    }

    override fun onError(e: Throwable) {
        fileCallBack?.onError(e)
    }

    override fun onNext(t: T) {
        fileCallBack?.onSuccess(t)
    }

}