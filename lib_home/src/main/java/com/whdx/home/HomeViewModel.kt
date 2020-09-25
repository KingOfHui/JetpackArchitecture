package com.whdx.home

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.User
import com.whdx.data.respository.UserRepository
import kotlinx.coroutines.delay

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class HomeViewModel(val userRepository: UserRepository) : BaseViewModel(){
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