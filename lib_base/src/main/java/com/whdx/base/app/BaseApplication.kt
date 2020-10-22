package com.whdx.base.app

import android.app.Application
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.github.jokar.multilanguages.library.MultiLanguage
import com.tencent.mmkv.MMKV
import com.whdx.base.language.LocalLanguageUtil
import com.whdx.base.util.ext.getNightMode
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
        //Save the system language selection when entering the app for the first time.
//        LocalManageUtil.saveSystemCurrentLanguage(base)
        MMKV.initialize(base)
        MultiLanguage.init { context -> //返回自己本地保存选择的语言设置
            LocalLanguageUtil.getSetLanguageLocale(context)
        }
        super.attachBaseContext(MultiLanguage.setLocal(base))
        APPLICATION = this
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        MultiLanguage.setApplicationLanguage(this)
//        (getSystemService(Context.UI_MODE_SERVICE) as UiModeManager).nightMode = getNightMode()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        /**
         * The user saves the system selection language when switching languages on the system settings page (in order to select when the system language is used, if it is not saved, it will not be available after switching languages)
         */
//        LocalLanguageUtil.saveSystemCurrentLanguage(applicationContext, newConfig)
        MultiLanguage.onConfigurationChanged(applicationContext)
    }

}