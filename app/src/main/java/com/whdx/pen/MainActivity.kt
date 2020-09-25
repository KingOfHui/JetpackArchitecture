package com.whdx.pen

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.data.respository.UserViewModel
import com.whdx.paper.pen.R
import com.whdx.paper.pen.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseBindingActivity<UserViewModel, ActivityMainBinding>() {
    override fun initVM(): UserViewModel = getViewModel()


    override fun setLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        val homeNavController = findNavController(R.id.nav_main_host_fragment)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(homeNavController,appBarConfiguration)
        bottom_navigation_view.setupWithNavController(homeNavController)
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