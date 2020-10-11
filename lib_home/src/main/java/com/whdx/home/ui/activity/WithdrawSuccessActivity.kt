package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.whdx.home.R
import com.wwy.android.ui.base.BaseActivity
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_withdraw_success.*
import java.util.concurrent.TimeUnit

class WithdrawSuccessActivity:BaseActivity() {
    var time:Int=3
    lateinit var subscribe: Disposable
    override fun setLayoutId()= R.layout.activity_withdraw_success
    override fun initView(savedInstanceState: Bundle?) {
        subscribe = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                tvFinish.text = String.format("返回(%sS)", --time)
                if (time == 0) {
                    finish()
                }
            }
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!subscribe.isDisposed) {
            subscribe.dispose()
        }
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,WithdrawSuccessActivity::class.java))
        }
    }
}