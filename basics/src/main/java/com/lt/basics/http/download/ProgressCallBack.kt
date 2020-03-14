package com.lt.basics.http.download

import android.util.Log
import com.lt.basics.bus.RxBus.Companion.default
import com.lt.basics.bus.RxSubscriptions.add
import com.lt.basics.bus.RxSubscriptions.remove
import com.lt.basics.http.download.DownLoadStateBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.*

/**
 * Created by HankGreen on 2017/9/26 0026.
 */
abstract class ProgressCallBack<T>(// 本地文件存放路径
        private val destFileDir: String, // 文件名
        private val destFileName: String) {
    private var mSubscription: Disposable? = null
    abstract fun onSuccess(t: T)
    abstract fun progress(progress: Long, total: Long)
    open fun onStart() {}
    open fun onCompleted() {}
    abstract fun onError(e: Throwable?)
    fun saveFile(body: ResponseBody) {
        var `is`: InputStream? = null
        val buf = ByteArray(2048)
        var len: Int
        var fos: FileOutputStream? = null
        try {
            `is` = body.byteStream()
            val dir = File(destFileDir)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val file = File(dir, destFileName)
            fos = FileOutputStream(file)
            while (`is`.read(buf).also { len = it } != -1) {
                fos.write(buf, 0, len)
            }
            fos.flush()
            //onCompleted();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`?.close()
                fos?.close()
                unsubscribe()
            } catch (e: IOException) {
                Log.e("saveFile", e.message)
            }
        }
    }

    /**
     * 订阅加载的进度条
     */
    fun subscribeLoadProgress() {
        mSubscription = default!!.toObservable(DownLoadStateBean::class.java)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe { progressLoadBean -> progress(progressLoadBean.bytesLoaded, progressLoadBean.total) }
        //将订阅者加入管理站
        add(mSubscription)
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    fun unsubscribe() {
        remove(mSubscription)
    }

    init {
        subscribeLoadProgress()
    }
}