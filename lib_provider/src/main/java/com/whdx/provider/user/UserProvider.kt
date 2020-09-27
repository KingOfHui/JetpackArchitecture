package com.whdx.base.provider.user

import com.alibaba.android.arouter.facade.template.IProvider
import com.whdx.data.data.user.User

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:46
 */
interface UserProvider:IProvider {
    fun getUserInfo(): User?

    fun removeUserInfo()

}