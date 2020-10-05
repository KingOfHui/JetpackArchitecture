package com.whdx.data.respository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.google.android.material.tabs.TabLayout
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0020 23:27
 */
class UserViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val mUser: MutableLiveData<User> = MutableLiveData()

    fun login(userName:String, password:String) {
        launchUI {
            doLoading()
            delay(2000)
            val login: ResultData<User>? = userRepository.login(userName, password)
            if (login is ResultData.Success) {
                doneSuccess()
                mUser.value = login.data
            } else if (login is ResultData.Error) {
//                SmartToast.complete("空")
//                doneEmpty()
                doneError(login.exception.message?:"")
            }
        }

    }
}