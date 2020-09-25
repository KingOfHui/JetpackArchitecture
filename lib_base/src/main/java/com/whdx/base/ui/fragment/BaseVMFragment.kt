package com.whdx.base.ui.fragment

import android.widget.TextView
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.kingja.loadsir.callback.SuccessCallback
import com.whdx.base.R
import com.whdx.base.common.callback.EmptyCallBack
import com.whdx.base.common.callback.ErrorCallBack
import com.whdx.base.common.callback.LoadingCallBack
import com.whdx.base.common.state.State
import com.whdx.base.common.state.StateType
import com.whdx.base.vm.BaseViewModel
import org.jetbrains.anko.find


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {
    protected lateinit var mViewModel: VM

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        mViewModel = initVM()
        mViewModel.loadState.observe(this, observer)
        startObserve()
    }

    abstract fun initVM(): VM

    abstract fun startObserve()

    open fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showError(msg: String) {
        loadService.setCallBack(ErrorCallBack::class.java) { _, view ->
            view.find<TextView>(R.id.tv_error).text = msg
        }
        loadService.showCallback(ErrorCallBack::class.java)
        SmartToast.show(msg)
    }

    open fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
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