package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.NetData
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.ProductItem
import com.whdx.data.data.topic.Topic
import com.whdx.data.data.user.User
import com.whdx.data.data.wallet.USDTBalance
import com.whdx.data.respository.UserRepository
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class HomeViewModel(val userRepository: UserRepository) : BaseLoadMoreViewModel<List<ProductItem>>() {

    val mUser: MutableLiveData<User> = MutableLiveData()
    val mTopic: MutableLiveData<List<Topic>> = MutableLiveData()
    val mProductItemList: MutableLiveData<MutableList<ProductItem>> = MutableLiveData()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    fun getTopic() {
        launchUI {
            val topic = userRepository.getTopic()
            if (topic is ResultData.Success) {
                mTopic.value = topic.data
            }
        }

    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        refreshing.value = true
        val productList = userRepository.getProductList(pageNum, 20)
        if (productList is ResultData.Success) {
            val items = productList.data.items

            mProductItemList.value?.let {
                it.addAll(items)
                mProductItemList.value = it
            }
            notifyResultToTopViewModel(items)
        }
    }

    fun onClick() {
        Timber.tag("dhdhdh").e("clicked")
        val user = User()
        user.username = "jflskd"
        mUser.value = user

    }
}