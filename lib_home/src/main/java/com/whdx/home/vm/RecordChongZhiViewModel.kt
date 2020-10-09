package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.ProductItem
import com.whdx.data.data.wallet.DepositRecordItem
import com.whdx.data.respository.UserRepository

class RecordChongZhiViewModel(private val userRepository: UserRepository):BaseLoadMoreViewModel<List<DepositRecordItem>>() {

    val depositRecordItemLive:MutableLiveData<MutableList<DepositRecordItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }
    override suspend fun load(isClear: Boolean, pageNum: Int) {
        val depositList = userRepository.getDepositList(pageNum, 20)

        if (isClear) {
            depositRecordItemLive.value?.clear()
        }
        if (depositList is ResultData.Success) {
            val items = depositList.data.items

            depositRecordItemLive.value?.let {
                it.addAll(items)
                depositRecordItemLive.value = it
            }
            notifyResultToTopViewModel(items)
        }
        refreshing.value = false
    }
}