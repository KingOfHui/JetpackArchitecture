package com.whdx.paper.pen

import com.whdx.base.app.BaseApplication
import com.whdx.pen.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:27
 */
class MyApplication :BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}