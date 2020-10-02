package com.whdx.home.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.whdx.base.provider.home.HomeProvider
import com.whdx.home.ui.activity.LoginActivity
import com.whdx.provider.PROVIDER_PATH_HOME

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:30
 */

@Route(path = PROVIDER_PATH_HOME)
class HomeProviderImpl:HomeProvider {
    override fun start(context: Context) {
        LoginActivity.start(context)
    }

    override fun init(context: Context?) {

    }
}