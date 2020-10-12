package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.wallet.MemberBonusItem
import com.whdx.data.respository.UserRepository

class MyTotalBonusViewModel(private val userRepository: UserRepository): BaseLoadMoreViewModel<List<MemberBonusItem>>() {
    val totalBonusListLive:MutableLiveData<List<MemberBonusItem>> = MutableLiveData()
    override suspend fun load(isClear: Boolean, pageNum: Int) {
        val totalBonusList = userRepository.getTotalBonusList(pageNum, 20)
        if (totalBonusList is ResultData.Success) {
            val items = totalBonusList.data.items
            totalBonusListLive.value= items
            notifyResultToTopViewModel(items)
        }
        refreshing.value = false
    }
}