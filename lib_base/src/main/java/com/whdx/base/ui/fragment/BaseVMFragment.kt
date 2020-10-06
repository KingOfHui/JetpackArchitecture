package com.whdx.base.ui.fragment

import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.R
import com.whdx.base.common.state.State
import com.whdx.base.common.state.StateType
import com.whdx.base.vm.BaseViewModel


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {
    protected lateinit var mViewModel: VM

    override fun onFragmentFirstVisible() {
        mViewModel = initVM()
        super.onFragmentFirstVisible()
        mViewModel.loadState.observe(this, observer)
        startObserve()
    }

    abstract fun initVM(): VM

    abstract fun startObserve()
    open fun showLoading(message: String? = getString(R.string.loading)) {
//        loadService.showCallback(LoadingCallBack::class.java)
        showProgressDialog(message)
    }

    open fun showSuccess() {
        SmartToast.complete("加载完成~")
        dismissProgressDialog()
//        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showError(msg: String?) {
//        loadService.setCallBack(ErrorCallBack::class.java) { _, view ->
//            view.find<TextView>(R.id.tv_error).text = msg
//        }
//        loadService.showCallback(ErrorCallBack::class.java)
        SmartToast.error(msg ?: requireContext().getString(R.string.errorMsg))
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
                    StateType.LOADING -> showLoading(it.message)
                    StateType.ERROR -> showError(it.message)
                    StateType.NETWORK_ERROR -> showError("网络出现问题啦")
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }
}