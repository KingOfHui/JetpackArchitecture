package com.whdx.home.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.provider.user.UserProvider
import com.whdx.data.data.user.User
import com.whdx.provider.PROVIDER_PATH_USER

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:58
 */

@Route(path = PROVIDER_PATH_USER)
class UserProviderImpl:UserProvider {
    override fun getUserInfo(): User? {
        SmartToast.complete("返回用户信息")
        return User()
    }

    override fun removeUserInfo() {

    }

    override fun init(context: Context?) {

    }
}