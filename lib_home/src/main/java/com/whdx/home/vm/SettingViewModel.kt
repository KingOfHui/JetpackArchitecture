package com.whdx.home.vm

import com.whdx.base.app.BaseApplication
import com.whdx.base.util.ext.packageInfo
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:53
 */
class SettingViewModel(private val userRepository: UserRepository):BaseViewModel() {

    fun getAppOnline() {
        launchUI {
        userRepository.getAppOnline(BaseApplication.CONTEXT.packageInfo().versionName)

        }
    }


}