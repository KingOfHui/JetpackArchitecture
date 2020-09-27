package com.whdx.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.User
import com.whdx.data.respository.UserRepository
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class HomeViewModel(val userRepository: UserRepository) : BaseLoadMoreViewModel<List<String>>() {
    val mUser: MutableLiveData<User> = MutableLiveData()
    val mList: MutableLiveData<MutableList<String>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    fun login(userName: String, password: String) {
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
                doneError(login.exception.message ?: "")
            }
        }
    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        val mutableListOf = mutableListOf<String>()
        for (i in 0..18) {
            mutableListOf.add("position $i")
        }
        mList.value?.let {
            if (isClear) {
                it.clear()
            }
            it.addAll(mutableListOf)
            mList.value = it
        }
        notifyResultToTopViewModel(mutableListOf)

        delay(2000)
        refreshing.value = false
    }

    fun onClick() {
        Timber.tag("dhdhdh").e("clicked")
        val user = User()
        user.username = "jflskd"
        mUser.value = user

    }
}