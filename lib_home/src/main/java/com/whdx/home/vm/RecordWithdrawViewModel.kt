package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.wallet.WithdrawRecordItem
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 7:59
 */
class RecordWithdrawViewModel(private val userRepository: UserRepository):BaseLoadMoreViewModel<List<WithdrawRecordItem>>() {
    val mWithdrawRecordLive:MutableLiveData<MutableList<WithdrawRecordItem>> = MutableLiveData()
    get() {if (field.value == null) field.value = mutableListOf();return field}

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        val withdrawList = userRepository.getWithdrawList(pageNum, 20)
        if (withdrawList is ResultData.Success) {
            val items = withdrawList.data.items
            if (isClear){
                mWithdrawRecordLive.value?.clear()
            }
            mWithdrawRecordLive.value?.let {
                it.addAll(items)
                mWithdrawRecordLive.value = it
            }
            notifyResultToTopViewModel(items)
        }
    }
}