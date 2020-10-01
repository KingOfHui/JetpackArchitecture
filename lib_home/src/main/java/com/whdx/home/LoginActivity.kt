package com.whdx.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.navigation.setupWithKeepStateNav
import com.whdx.home.vm.HomeViewModel
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
        setupWithKeepStateNav(R.id.nav_login_host_fragment, R.navigation.nav_graph_login)
    }

    override fun initData() {
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}