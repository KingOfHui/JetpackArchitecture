package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.whdx.base.language.LocalLanguageUtil
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.vm.SettingViewModel
import kotlinx.android.synthetic.main.activity_language.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LanguageActivity: BaseVMActivity<SettingViewModel>() {
    var language:Int = 3
    override fun initVM(): SettingViewModel= getViewModel()
    override fun startObserve() {
    }

    override fun setLayoutId()= R.layout.activity_language

    override fun initView(savedInstanceState: Bundle?) {
        rgSelect.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.tv_en->language = 3
                R.id.tv_cn->language = 1
            }
        }
        tvSure.clickWithTrigger {
            LocalLanguageUtil.saveSelectLanguage(this,language)
            MainActivity.start(this)
            finish()
        }
    }

    override fun initData() {
    }
    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,LanguageActivity::class.java))
        }
    }
}