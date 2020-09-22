package com.whdx.base.app

import android.app.Application
import android.app.UiModeManager
import android.content.Context
import android.util.TypedValue
import com.coder.zzq.smartshow.core.SmartShow
import com.whdx.base.BuildConfig
import com.whdx.base.R
import com.whdx.base.common.callback.EmptyCallBack
import com.whdx.base.common.callback.ErrorCallBack
import com.whdx.base.common.callback.LoadingCallBack
import com.whdx.base.ext.getNightMode
import com.whdx.base.ext.resourceId
import com.whdx.base.util.ActivityHelper
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0022 8:55
 */
open class BaseApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        ActivityHelper.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initLoadSir()
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(
                TypedValue().resourceId(R.attr.colorPrimary, context.theme),
                TypedValue().resourceId(R.attr.textColorPrimary, context.theme)
            ) //全局设置主题颜色
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
//        LoadMoreModuleConfig.defLoadMoreView = CustomLoadMoreView()
        MMKV.initialize(this)
        LiveEventBus.config()
        SmartShow.init(this)
        (getSystemService(Context.UI_MODE_SERVICE) as UiModeManager).nightMode = getNightMode()
    }
    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ErrorCallBack())
            .addCallback(LoadingCallBack())
            .addCallback(EmptyCallBack())
            .commit()
    }
}