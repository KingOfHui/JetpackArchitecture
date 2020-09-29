package com.whdx.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.navigation.setupBottomNavigationViewWithTabNav
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:47
 */
class LoginActivity : BaseVMActivity<HomeViewModel>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutId() = R.layout.activity_login;

    override fun initView(savedInstanceState: Bundle?) {
        setupBottomNavigationViewWithTabNav(R.id.nav_login_host_fragment, R.navigation.login_navigation)
    }

    override fun initData() {
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}