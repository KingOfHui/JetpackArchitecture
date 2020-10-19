package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.ext.clickToCopy
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.databinding.ActivityExportKeyBinding
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.activity_export_key.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/19 0019 8:28
 */
class ExportKeyActivity: BaseBindingActivity<WalletViewModel, ActivityExportKeyBinding>() {
    override fun initVM(): WalletViewModel= getViewModel()

    override fun startObserve() {

        mViewModel.mCurrentWallet.observe(this, Observer {
            txtPublicKey.text = it.publicKey
            val privateKey = it.privateKey
            editPrivateKey.text = privateKey
            btnCopy.clickWithTrigger { privateKey?.clickToCopy(this) }
        })
    }

    override fun setLayoutId()= R.layout.activity_export_key

    override fun initView(savedInstanceState: Bundle?) {
        titleBar.setOnLeftClickListener { finish() }
    }

    override fun initData() {
        mViewModel.getCurrentWallet()
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,ExportKeyActivity::class.java))
        }
    }
}