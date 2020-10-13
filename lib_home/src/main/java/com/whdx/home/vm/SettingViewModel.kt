package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.app.BaseApplication
import com.whdx.base.util.ext.packageInfo
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.UpdateVersion
import com.whdx.data.data.base.ResultData
import com.whdx.data.respository.UserRepository
import com.whdx.data.respository.base.BaseDataSource

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:53
 */
class SettingViewModel(private val userRepository: UserRepository):BaseViewModel() {

    val updateVersionLive:MutableLiveData<UpdateVersion> = MutableLiveData()

    fun getAppOnline() {
        launchUI {
        val appOnline =
            userRepository.getAppOnline(BaseApplication.CONTEXT.packageInfo().versionName)
            if (appOnline is ResultData.Success) {
                updateVersionLive.value = appOnline.data
            }else if (appOnline is ResultData.Error){
                if (appOnline.exception !is BaseDataSource.VersionEmptyException) {
                    SmartToast.error(appOnline.exception.message?:"")
                }
            }
        }
    }


}