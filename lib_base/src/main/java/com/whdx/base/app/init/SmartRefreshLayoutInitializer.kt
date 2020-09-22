package com.whdx.base.app.init

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer
import com.whdx.base.R

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/23 0023 0:06
 */
class SmartRefreshLayoutInitializer : Initializer() {
    override fun init(application: Application) {
        /*//设置全局的Header构建器
               SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                   layout.setPrimaryColorsId(
                       TypedValue().resourceId(R.attr.colorPrimary, context.theme),
                       TypedValue().resourceId(R.attr.textColorPrimary, context.theme)
                   ) //全局设置主题颜色
                   ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
               }*/
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.apply {
                setHeaderHeight(110f)
                setHeaderTriggerRate(0.8f)
                setDisableContentWhenLoading(false)
                setPrimaryColorsId(R.color.accent_brown,R.color.colorPrimary)
            }
        }
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator{ClassicsHeader()}
        /*

        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator() {
            @NonNull
            fun createRefreshFooter(
                @NonNull context: Context?,
                @NonNull layout: RefreshLayout?
            ): RefreshFooter? {
                return FalsifyFooter(context)
            }
        })


        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator() {
            @NonNull
            fun createRefreshHeader(
                @NonNull context: Context?,
                @NonNull layout: RefreshLayout?
            ): RefreshHeader? {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
//				return new ClassicsHeader(context);
                return StoreHouseHeader(context).initWithString("JIN GUI")
            }
        })*/
    }
}