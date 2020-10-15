package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.ext.clearToast
import com.whdx.base.util.ext.compareVersionCode
import com.whdx.base.util.ext.getLanguage
import com.whdx.base.util.navigation.setupBottomNavigationViewWithKeepStateNav
import com.whdx.home.BuildConfig
import com.whdx.home.R
import com.whdx.home.databinding.ActivityMainBinding
import com.whdx.home.ui.dialog.UpdateDialog
import com.whdx.home.vm.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : BaseBindingActivity<HomeViewModel, ActivityMainBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun setLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        val navController = setupBottomNavigationViewWithKeepStateNav(
            R.id.nav_main_host_fragment,
            R.navigation.nav_graph_home
        )
//        val navController = findNavController(R.id.nav_main_host_fragment)
        bottom_navigation_view.setupWithNavController(navController)
        bottom_navigation_view.clearToast(
            listOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_wallet,
                R.id.navigation_notifications
            )
        )
    }

    override fun initData() {
        mViewModel.getAppOnline()
    }

    override fun startObserve() {
        mViewModel.run {
            updateVersionLive.observe(this@MainActivity, Observer {
                if (BuildConfig.VERSION_NAME.compareVersionCode(it.version ?: "")) {
                    mViewModel.updateVersionLive.value?.let { version ->
                        if (it.internal_android_url.isNullOrEmpty()) {
                            SmartToast.error(getString(R.string.update_address_no))
                            return@let
                        }
                        UpdateDialog(this@MainActivity) {

                            val intent = Intent().apply {
                                action = "android.intent.action.VIEW"
                                data =
                                    Uri.parse(if (getLanguage() == 1) it.internal_android_url else it.abroad_android_url)
                            }
                            startActivity(intent)
                        }.show()
                    }
                }
            })
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }

    }


}