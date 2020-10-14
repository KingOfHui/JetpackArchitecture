package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.ActivityHelper
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.compareVersionCode
import com.whdx.data.respository.base.LocalDataSource
import com.whdx.home.BuildConfig
import com.whdx.home.R
import com.whdx.home.vm.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:53
 */
class SettingActivity : BaseVMActivity<SettingViewModel>() {
    override fun initVM(): SettingViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.updateVersionLive.observe(this, Observer {
            if (BuildConfig.VERSION_NAME.compareVersionCode(it.version ?: "")) {
                tv_version.text = String.format(getString(R.string.update_to), it.version)
                tv_update_version.isClickable = true
            }
        })
    }

    override fun setLayoutId() = R.layout.activity_setting

    override fun initView(savedInstanceState: Bundle?) {
        tv_update_version.isClickable = false
        titleBar.setOnLeftClickListener { finish() }
        tvLanguage.clickWithTrigger {
            LanguageActivity.start(this)
        }
        tv_update_version.clickWithTrigger {
            mViewModel.updateVersionLive.value?.let { version ->
                if (version.internal_android_url.isNullOrEmpty()) {
                    return@clickWithTrigger
                }
                val intent = Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse(version.internal_android_url)
                }
                startActivity(intent)
            }
        }
        tvCancel.clickWithTrigger {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) { LocalDataSource().walletDao.deleteAllWallet() }
                ActivityHelper.finishAll()
                LoginActivity.start(this@SettingActivity)
                overridePendingTransition(0,0)
            }
        }
    }

    override fun initData() {
        mViewModel.getAppOnline()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }
    }
}