package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.ui.setupWithNavController
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.navigation.setupBottomNavigationViewWithKeepStateNav
import com.whdx.data.respository.UserViewModel
import com.whdx.home.R
import com.whdx.home.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseBindingActivity<UserViewModel, ActivityMainBinding>() {
    override fun initVM(): UserViewModel = getViewModel()

    override fun setLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        val navController = setupBottomNavigationViewWithKeepStateNav(
            R.id.nav_main_host_fragment,
            R.navigation.nav_graph_home
        )
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

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

    }


}