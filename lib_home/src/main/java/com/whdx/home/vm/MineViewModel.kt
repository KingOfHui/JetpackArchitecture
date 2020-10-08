package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.InviteData
import com.whdx.data.data.user.InviteListItem
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class MineViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<InviteListItem>>() {

    val inviteData: MutableLiveData<InviteData> = MutableLiveData()
    val inviteListLive: MutableLiveData<MutableList<InviteListItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    fun requestInviteData() {
        launchUI {
            val requestInviteData = userRepository.requestInviteData()
            if (requestInviteData is com.whdx.data.data.base.ResultData.Success) {
                inviteData.value = requestInviteData.data
            }
            refresh()
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

}