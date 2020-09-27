package com.whdx.base.provider.home

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:23
 */
interface HomeProvider:IProvider {
    fun start(context: Context)
}