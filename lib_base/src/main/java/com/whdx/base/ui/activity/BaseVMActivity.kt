package com.whdx.base.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.R
import com.whdx.base.common.state.State
import com.whdx.base.common.state.StateType
import com.whdx.base.vm.BaseViewModel
import com.wwy.android.ui.base.BaseActivity
import org.jetbrains.anko.find


/**
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {
    lateinit var mViewModel: VM

    override fun initActivity(savedInstanceState: Bundle?) {
        mViewModel = initVM()
        mViewModel.loadState.observe(this, observer)
        startObserve()
        super.initActivity(savedInstanceState)
    }

    abstract fun initVM(): VM

    abstract fun startObserve()

    open fun showLoading() {
//        loadService.showCallback(LoadingCallBack::class.java)
        showProgressDialog(R.string.agentweb_download)
    }

    open fun showSuccess() {
        SmartToast.complete("加载完成~")
        dismissProgressDialog()
//        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showError(msg: String) {
//        loadService.setCallBack(ErrorCallBack::class.java) { _, view ->
//            view.find<TextView>(R.id.tv_error).text = msg
//        }
//        loadService.showCallback(ErrorCallBack::class.java)
        SmartToast.error(msg)
        dismissProgressDialog()
    }

    open fun showEmpty() {
//        loadService.showCallback(EmptyCallBack::class.java)
        SmartToast.showInCenter("空内容~")
        dismissProgressDialog()
    }

    /**
     * 分发应用状态
     */
    private val observer by lazy {
        Observer<State> {
            it?.let {
                when (it.code) {
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showError(it.message)
                    StateType.NETWORK_ERROR -> showError("网络出现问题啦")
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }
}