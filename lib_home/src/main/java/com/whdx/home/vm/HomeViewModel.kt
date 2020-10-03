package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.whdx.base.vm.BaseLoadMoreViewModel
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
                val map = Transformations.map(mUser) {
                    it.icon
                }
                val s :LiveData<String> = Transformations.switchMap(mUser){
                    val live = MutableLiveData<String>()
                    live
                }
                mUser.value = login.data
                mList.value?.addAll(login.data.id_list?: listOf())
            } else if (login is ResultData.Error) {
//                SmartToast.complete("空")
//                doneEmpty()
                doneError(login.exception.message ?: "")
            }
        }
    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        refreshing.value = false
        val mutableListOf = mutableListOf<String>()
        for (i in 0..18) {
            mutableListOf.add("position $i")
        }
        mList.value?.let {
            if (isClear) {
                it.clear()
            }
            it.addAll(mutableListOf)
        }
        notifyResultToTopViewModel(mutableListOf)

//        delay(2000)
    }

    fun onClick() {
        Timber.tag("dhdhdh").e("clicked")
        val user = User()
        user.username = "jflskd"
        mUser.value = user

    }
}