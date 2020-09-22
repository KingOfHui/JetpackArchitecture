package com.whdx.base.ui.fragment

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
        startObserve()
        super.onFragmentFirstVisible()
    }


    abstract fun initVM(): VM

    abstract fun startObserve()

}