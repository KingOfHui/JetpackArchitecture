package com.whdx.base.provider.home

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.whdx.provider.PROVIDER_PATH_HOME

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:27
 */
class HomeProviderWrap private constructor(){

    @Autowired(name = PROVIDER_PATH_HOME)
    lateinit var provider:HomeProvider

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context) {
        provider.start(context)
    }

    companion object{
        val instance = Singleton.holder

        object Singleton {
            val holder = HomeProviderWrap()
        }
    }
}