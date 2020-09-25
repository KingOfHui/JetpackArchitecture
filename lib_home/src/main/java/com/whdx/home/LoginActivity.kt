package com.whdx.home

import android.os.Bundle
import com.whdx.base.ui.activity.BaseVMActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:47
 */
class LoginActivity:BaseVMActivity<HomeViewModel>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutId()= R.layout.activity_login;

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }
}