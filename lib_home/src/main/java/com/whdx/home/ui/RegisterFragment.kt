package com.whdx.home.ui

import androidx.navigation.fragment.findNavController
import com.whdx.base.ui.fragment.BaseVMFragment
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:50
 */
class RegisterFragment :BaseVMFragment<HomeViewModel>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_register;

    override fun initView() {
        register.setOnClickListener { findNavController().navigate(R.id.action_navigation_register_to_navigation_forget) }
    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData RegisterFragment")

    }
}