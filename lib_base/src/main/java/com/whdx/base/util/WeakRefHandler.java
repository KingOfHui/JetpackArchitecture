package com.whdx.base.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 使用弱引用的Handler，防内存泄漏
 * 使用方法：new WeakRefHandler(this);然后Activity或Fragment实现Callback接口
 * 例如：
 * public class HomeActivity implements WeakRefHandler.Callback{
 *     WeakRefHandler mHandler=new WeakRefHandler(this);
 *      @Override
 *     public void handleMessage(Message msg) {
 *         ...
 *     }
 * }
 * 注意：构造方法传入的callback不要用匿名内部类实现，否则会被GC，推荐用Activity或Fragment本身
 */
public class WeakRefHandler extends Handler {
    private WeakReference<Callback> mWeakReference;

    /**
     * callback不要传匿名内部类实现的变量
     * @param callback 处理消息的回调
     */
    public WeakRefHandler(Callback callback) {
        mWeakReference = new WeakReference<>(callback);
    }

    public WeakRefHandler(Callback callback, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Callback callback = mWeakReference.get();
            callback.handleMessage(msg);
        }
    }

    public interface Callback {
        void handleMessage(Message msg);
    }
}