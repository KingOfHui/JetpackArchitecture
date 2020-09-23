package com.whdx.base.app

import android.app.Application
import android.app.UiModeManager
import android.content.Context
import com.whdx.base.ext.getNightMode
import kotlin.properties.Delegates

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0022 8:55
 */
open class BaseApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
        var APPLICATION: Application by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        APPLICATION = this
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        (getSystemService(Context.UI_MODE_SERVICE) as UiModeManager).nightMode = getNightMode()
    }

}