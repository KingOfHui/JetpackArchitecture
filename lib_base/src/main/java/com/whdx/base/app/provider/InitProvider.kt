package com.whdx.base.app.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.coder.zzq.smartshow.core.SmartShow
import com.jeremyliao.liveeventbus.LiveEventBus
import com.tencent.mmkv.MMKV
import com.whdx.base.BuildConfig
import com.whdx.base.app.BaseApplication.Companion.APPLICATION
import com.whdx.base.app.init.InitializerFactory
import com.whdx.base.util.ActivityHelper
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0022 23:36
 */
class InitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.i("onCreate:InitProvider")
        initLoadSir()
        InitializerFactory.init(APPLICATION)

//        LoadMoreModuleConfig.defLoadMoreView = CustomLoadMoreView()
        MMKV.initialize(context)
        ActivityHelper.init(APPLICATION)
        SmartShow.init(APPLICATION)
        LiveEventBus.config()

        return true
    }
    private fun initLoadSir() {
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? = null
    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? = null

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int = 0

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int = 0

    override fun getType(p0: Uri): String? = null
}