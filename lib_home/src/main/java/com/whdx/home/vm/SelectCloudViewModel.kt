package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel

class SelectCloudViewModel : BaseLoadMoreViewModel<List<String>>() {
    val mList: MutableLiveData<MutableList<String>> = MutableLiveData<MutableList<String>>()
        get() {
            if (field.value == null) field.value = mutableListOf()
            return field
        }

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        refreshing.value = true
        val mutableListOf = mutableListOf<String>()
        for (i in 0..18) {
            mutableListOf.add("position $i")
        }
        mList.value?.let {
            if (isClear) {
                it.clear()
            }
            it.addAll(mutableListOf)
            mList.value = it
        }
        notifyResultToTopViewModel(mutableListOf)
        refreshing.value = false
    }
}