package com.whdx.provider.user

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.whdx.base.provider.home.HomeProviderWrap
import com.whdx.base.provider.user.UserProvider
import com.whdx.data.data.user.User
import com.whdx.provider.PROVIDER_PATH_USER

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/27 0027 14:54
 */
class UserProviderWrap private constructor(){

    @Autowired(name = PROVIDER_PATH_USER)
    lateinit var provider: UserProvider

    init {
        ARouter.getInstance().inject(this)
    }
    fun getUserInfo(): User?{
        return provider.getUserInfo()
    }

    fun removeUserInfo(){
        provider.removeUserInfo()
    }

    companion object{
        val instance = Singleton.holder

        object Singleton {
            val holder = UserProviderWrap()
        }
    }
}