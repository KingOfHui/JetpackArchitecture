package com.whdx.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.navigation.NormalKeepStateNavigator
import com.whdx.base.util.navigation.TabNavHostFragment
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
        val navController = findNavController(R.id.nav_login_host_fragment)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_login_host_fragment)!!

        val navigator = TabNavHostFragment.NormalNavigator(
            this,
            navHostFragment.childFragmentManager,
            R.id.nav_login_host_fragment
        )
        navController.navigatorProvider.addNavigator(navigator)

        navController.setGraph(R.navigation.login_navigation)
    }

    override fun initData() {
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}