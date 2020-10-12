package com.whdx.pen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.whdx.home.ui.activity.LoginActivity
import com.whdx.home.ui.activity.MainActivity
import com.whdx.home.vm.WalletViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 16:51
 */
class SplashActivity : AppCompatActivity() {


//    override fun setLayoutId()= R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mViewModel: WalletViewModel = getViewModel()
        mViewModel.getCurrentWallet()
        mViewModel.hasWallet.observe(this, Observer {
            runBlocking { delay(300) }
            if (it) MainActivity.start(this) else LoginActivity.start(
                this
            )
            finish()
        })
    }
}