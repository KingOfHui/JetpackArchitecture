package com.whdx.base.app.init

import android.app.Application
import android.graphics.Color
import com.scwang.smartrefresh.header.StoreHouseHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.footer.FalsifyFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
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
                setPrimaryColorsId(R.color.qmui_config_color_transparent, R.color.white)
            }
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ ->
//            ClassicsHeader(application)
            StoreHouseHeader(application).initWithString("Bit Talk")
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ ->  ClassicsFooter(application).setAccentColor(Color.parseColor("#8E94A9")) }
    }
}