package com.whdx.pen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.whdx.home.ui.activity.LoginActivity
import com.whdx.home.ui.activity.MainActivity
import com.whdx.home.vm.WalletViewModel
import com.whdx.paper.pen.R
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 16:51
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
        val mViewModel: WalletViewModel = getViewModel()
        mViewModel.getCurrentWallet()
        mViewModel.hasWallet.observe(this, Observer {
            mViewModel.launchUI {
                async { delay(2000) }.await()
                if (it) {
                    MainActivity.start(this@SplashActivity)
                } else{
                    LoginActivity.start(this@SplashActivity)
                }
                finish()
            }
        })
    }
}