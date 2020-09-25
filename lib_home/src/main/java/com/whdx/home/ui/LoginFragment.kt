package com.whdx.home.ui

import androidx.navigation.Navigation
import com.whdx.base.ui.fragment.BaseVMFragment
import com.whdx.home.HomeViewModel
import com.whdx.home.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:50
 */
class LoginFragment :BaseVMFragment<HomeViewModel>() {
    override fun initVM(): HomeViewModel= getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_login;

    override fun initView() {
        login.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_login_to_navigation_register);
        }
        toForget.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_login_to_navigation_forget)
        }
    }

    override fun initData() {
    }
}