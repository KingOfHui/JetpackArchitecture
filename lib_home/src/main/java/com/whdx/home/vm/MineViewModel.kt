package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.app.BaseApplication
import com.whdx.base.util.ext.clickToCopy
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.InviteData
import com.whdx.data.data.user.InviteListItem
import com.whdx.data.data.user.UserInfo
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class MineViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<InviteListItem>>() {

    val inviteData: MutableLiveData<InviteData> = MutableLiveData()
    val userInfoLive: MutableLiveData<UserInfo> = MutableLiveData()
    val inviteListLive: MutableLiveData<MutableList<InviteListItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }
    val openSuccess:MutableLiveData<Boolean> = MutableLiveData()

    fun requestInviteData() {
        launchUI {
            val requestInviteData = userRepository.requestInviteData()
            if (requestInviteData is com.whdx.data.data.base.ResultData.Success) {
                inviteData.value = requestInviteData.data
            }
            getUserInfo()
            refresh()
        }
    }

    fun openBid(code: String) {
        launchUI { val openBid = userRepository.openBid(code)
            if (openBid is ResultData.Success) {
                SmartToast.successLong("恭喜您BID已成功开通")
                openSuccess.value = true
                getUserInfo()
            }
        }
    }
    suspend fun getUserInfo() {
         val userInfo = userRepository.getUserInfo()
            if (userInfo is ResultData.Success) {
                userInfoLive.value = userInfo.data
            }

    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        if (isClear) {
            inviteListLive.value?.clear()
        }
        val inviteList = userRepository.getInviteList(pageNum, 20)
        if (inviteList is ResultData.Success) {
            val elements = inviteList.data.items
            inviteListLive.value?.let {
                it.addAll(elements)
                inviteListLive.value = it

            }
            notifyResultToTopViewModel(elements)
        }

        refreshing.value = false
    }

    fun copyAddress() {
        userInfoLive.value?.address?.clickToCopy(BaseApplication.CONTEXT)
    }

}