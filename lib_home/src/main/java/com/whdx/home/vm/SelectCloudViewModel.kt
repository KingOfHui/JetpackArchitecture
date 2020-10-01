package com.whdx.home.vm

import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel

class SelectCloudViewModel:BaseLoadMoreViewModel<List<String>>() {
    override suspend fun load(isClear: Boolean, pageNum: Int) {

    }
}