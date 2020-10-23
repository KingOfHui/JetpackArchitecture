package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.data.data.wallet.WalletModel
import com.whdx.home.R
import com.whdx.home.vm.WalletManageViewModel
import kotlinx.android.synthetic.main.activity_wallet_detail.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/23 0023 15:35
 */
class WalletDetailActivity : BaseBindingActivity<WalletManageViewModel, ViewDataBinding>() {
    override fun initVM(): WalletManageViewModel = getViewModel()
    override fun setLayoutId() = R.layout.activity_wallet_detail

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        val walletModel = intent.extras?.getSerializable("wallet_model") as WalletModel
        tvAddress.text = walletModel.address
        tvWalletName.text = walletModel.name
        tvWalletNameTip.clickWithTrigger {
            WalletNameChangeActivity.start(this, walletModel)
        }
        tvExportKey.clickWithTrigger {
            ExportKeyActivity.start(this)
        }
    }

    override fun startObserve() {
    }

    companion object {
        fun start(context: Context, walletModel: WalletModel) {
            context.startActivity(Intent(context, WalletDetailActivity::class.java).apply {
                putExtra("wallet_model", walletModel)
            })
        }
    }
}