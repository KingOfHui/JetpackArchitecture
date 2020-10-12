package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.ActivityHelper
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.vm.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:53
 */
class SettingActivity : BaseVMActivity<SettingViewModel>() {
    override fun initVM(): SettingViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutId() = R.layout.activity_setting

    override fun initView(savedInstanceState: Bundle?) {
        titleBar.setOnLeftClickListener { finish() }
        tvLanguage.clickWithTrigger {
            LanguageActivity.start(this)
        }
        tv_update_version.clickWithTrigger {
            val intent = Intent().apply {
                action = "android.intent.action.VIEW"
                data = Uri.parse("https://www.baidu.com")
            }
            startActivity(intent)
        }
        tvCancel.clickWithTrigger {
            ActivityHelper.finishAll()
            LoginActivity.start(this)
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