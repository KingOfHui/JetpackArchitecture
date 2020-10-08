package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.MyStorage
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.InvestProductItem
import com.whdx.data.respository.UserRepository

class MyCloudViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<InvestProductItem>>() {

    val mInvestList: MutableLiveData<MutableList<InvestProductItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }
    val mMyStorage: MutableLiveData<MyStorage> = MutableLiveData()

    fun getMyStorage() {
        launchUI {
            val response = userRepository.getMyStorage()
            if (response is ResultData.Success) {
                mMyStorage.value = response.data
            }
        }
    }


    override suspend fun load(isClear: Boolean, pageNum: Int) {
        if (isClear) {
            mInvestList.value?.clear()
        }
        val investList = userRepository.getInvestList(pageNum, 20)
        if (investList is ResultData.Success) {
            val items = investList.data.items
            mInvestList.value?.let {
                it.addAll(items)
                mInvestList.value = it
            }
            notifyResultToTopViewModel(items)
        }
    }

}