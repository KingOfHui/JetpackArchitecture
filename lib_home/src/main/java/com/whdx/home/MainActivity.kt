package com.whdx.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.navigation.KeepStateNavigator
import com.whdx.data.respository.UserViewModel
import com.whdx.home.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseBindingActivity<UserViewModel, ActivityMainBinding>() {
    override fun initVM(): UserViewModel = getViewModel()

    override fun setLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_main_host_fragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_main_host_fragment)!!

        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_main_host_fragment)
        navController.navigatorProvider.addNavigator(navigator)

        navController.setGraph(R.navigation.home_nav_graph)
        bottom_navigation_view.setupWithNavController(navController)
    }

    override fun initData() {
    }

    override fun startObserve() {
        mViewModel.run {
            mUser.observe(this@MainActivity, Observer {
                SmartToast.complete(mUser.value?.username)
            })
        }
    }


}