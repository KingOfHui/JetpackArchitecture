package com.whdx.pen

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.ktx.immersionBar
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
        setContentView(R.layout.activity_splash)
        immersionBar { statusBarColor(R.color.color_F7F7FA) }
        ObjectAnimator.ofInt(findViewById(R.id.fl_bg), "alpha", 0, 1).setDuration(1000).start()
        val mViewModel: WalletViewModel = getViewModel()
        mViewModel.getCurrentWallet()
        mViewModel.hasWallet.observe(this, Observer {
            lifecycleScope.launch {
                if (it) {
                    MainActivity.start(this@SplashActivity)
                } else{
                    LoginActivity.start(this@SplashActivity)
                }
                overridePendingTransition(0,0)
                finish()
            }
        })
    }

}