package com.whdx.base.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.whdx.base.common.state.State
import com.whdx.base.common.state.StateType
import kotlinx.coroutines.*

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
open class BaseViewModel() : ViewModel(), LifecycleObserver {

    val loadState by lazy {
        MutableLiveData<State>()
    }
    /**
     * 运行在UI线程的协程 viewModelScope 已经实现了在onCleared取消协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        block()
    }

    //回调加载中视图
    fun doLoading() {
        loadState.postValue(State(StateType.LOADING))
    }

    //回调错误视图
    fun doneError(msg: String = "") {
        loadState.postValue(State(StateType.ERROR, msg))
    }

    //回调成功视图
    fun doneSuccess() {
        loadState.postValue(State(StateType.SUCCESS))
    }

    //回调空视图
    fun doneEmpty() {
        loadState.postValue(State(StateType.EMPTY))
    }
}