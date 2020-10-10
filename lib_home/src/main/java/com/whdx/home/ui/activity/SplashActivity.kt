package com.whdx.home.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.whdx.base.R
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.home.vm.WalletViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 16:51
 */
class SplashActivity:BaseVMActivity<WalletViewModel>() {


    override fun setLayoutId()= R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        mViewModel.getCurrentWallet()
    }

    override fun initVM():WalletViewModel=getViewModel()

    override fun startObserve() {
        mViewModel.hasWallet.observe(this, Observer {
//            lifecycleScope.launch { delay(1000) }
            if (it) MainActivity.start(this) else LoginActivity.start(this)
            finish()
        })
    }
}