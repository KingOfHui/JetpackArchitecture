package com.whdx.data.respository

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
            val login: ResultData<User>? = withContext(Dispatchers.IO) {
                userRepository.login(userName, password)
            }
            if (login is ResultData.Success) {
                doneSuccess()
                mUser.value = login.data
            } else if (login is ResultData.Error) {
                doneError(login.exception.message?:"")
            }
        }

    }
}