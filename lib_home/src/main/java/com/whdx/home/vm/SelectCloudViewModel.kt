package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.ProductItem
import com.whdx.data.respository.UserRepository

class SelectCloudViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<ProductItem>>() {
    val mProductItemList: MutableLiveData<MutableList<ProductItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        refreshing.value = true
        if (isClear) {
            mProductItemList.value?.clear()
        }
        val productList = userRepository.getProductList(pageNum, 20)
        if (productList is ResultData.Success) {
            val items = productList.data.items
            mProductItemList.value?.let {
                it.addAll(items)
                mProductItemList.value = it
            }?:let {
                if (isClear) {
                    doneEmpty()
                }
            }
            notifyResultToTopViewModel(items)
        } else if (productList is ResultData.Error) {
            doneError(productList.exception.message ?: "")
        }
        refreshing.value = false
    }
}