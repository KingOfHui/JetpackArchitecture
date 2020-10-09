package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.wallet.InvestBonusItem
import com.whdx.data.respository.UserRepository

class BonusViewModel(private val userRepository: UserRepository):BaseLoadMoreViewModel<List<InvestBonusItem>>() {

    val mInvestBonusItemLive:MutableLiveData<MutableList<InvestBonusItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    override suspend fun load(isClear: Boolean, pageNum: Int) {

        val bonus = userRepository.getInvestBonusList(pageNum, 20, any as Int)
        if (isClear) {
            mInvestBonusItemLive.value?.clear()
        }
        if (bonus is ResultData.Success) {
            val items = bonus.data.items
            mInvestBonusItemLive.value?.let {
                it.addAll(items)
                mInvestBonusItemLive.value = it
            }
            notifyResultToTopViewModel(items)
        }
        refreshing.value = false

    }
}