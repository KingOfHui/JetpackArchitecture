package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.home.R
import com.whdx.home.vm.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:53
 */
class SettingActivity: BaseVMActivity<SettingViewModel>() {
    override fun initVM(): SettingViewModel= getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutId()= R.layout.activity_setting

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,SettingActivity::class.java))
        }
    }
}