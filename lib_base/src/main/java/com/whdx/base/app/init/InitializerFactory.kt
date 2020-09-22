package com.whdx.base.app.init

import android.app.Application
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/23 0023 0:03
 */
class InitializerFactory {

    companion object{
        private val configs = listOf<Initializer>(
            SmartRefreshLayoutInitializer()
        )
        fun init(application: Application) {
            configs.forEach { it.init(application) }
        }
    }
}