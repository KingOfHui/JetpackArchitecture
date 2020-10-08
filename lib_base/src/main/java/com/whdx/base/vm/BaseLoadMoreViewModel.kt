package com.whdx.base.vm

import androidx.lifecycle.MutableLiveData

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 15:57
 */
abstract class BaseLoadMoreViewModel<DATA : List<*>> : BaseViewModel() {
    val refreshing = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()
    val autoRefresh = MutableLiveData<Boolean>()//SmartRefreshLayout自动刷新标记
    private val INIT_PAGE_NUM = 1
    private var pageNum = 1

    fun refresh() {
        pageNum = INIT_PAGE_NUM
        launchUI {
            load(true, pageNum)
        }
    }

    fun tryToLoadNext() {
        launchUI {
            load(false, pageNum)
        }
    }

    protected fun notifyResultToTopViewModel(data: DATA?, size: Int = 20) {
        if (data != null && data.size > 0) {
            pageNum++
            hasMore.value = data.size >= size
        } else{
            hasMore.value = false
        }
    }

    protected abstract suspend fun load(isClear: Boolean, pageNum: Int)


    //下拉刷新
    /*var onRefreshCommand: BindingCommand<Any> = BindingCommand(object : BindingAction {
        override fun call() {
            refresh()
        }
    })

    //上拉加载
    var onLoadMoreCommand: BindingCommand<Any> = BindingCommand(object : BindingAction {
        override fun call() {
            tryToLoadNext()
        }
    })*/
}