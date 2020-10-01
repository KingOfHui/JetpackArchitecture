package com.whdx.home.ui

import com.whdx.base.ui.fragment.BaseVMFragment
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:50
 */
class ForgetPwdFragment :BaseVMFragment<HomeViewModel>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_forget_password;

    override fun initView() {
    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData ForgetPwdFragment")

    }
}