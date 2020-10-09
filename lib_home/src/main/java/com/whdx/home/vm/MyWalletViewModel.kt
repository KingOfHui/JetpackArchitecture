package com.whdx.home.vm

import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 16:34
 */
class MyWalletViewModel(private val userRepository: UserRepository):BaseLoadMoreViewModel<List<String>>() {
    override suspend fun load(isClear: Boolean, pageNum: Int) {

    }
}