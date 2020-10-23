package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.data.data.wallet.WalletModel
import com.whdx.home.R
import com.whdx.home.vm.WalletManageViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/23 0023 16:15
 */
class WalletNameChangeActivity: BaseBindingActivity<WalletManageViewModel, ViewDataBinding>() {
    override fun initVM(): WalletManageViewModel= getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutId()= R.layout.activity_wallet_name_change

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    companion object{
        fun start(
            context: Context,
            walletModel: WalletModel
        ) {
            context.startActivity(Intent(context,WalletNameChangeActivity::class.java))
        }
    }
}