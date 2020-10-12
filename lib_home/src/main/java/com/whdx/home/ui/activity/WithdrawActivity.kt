package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.databinding.ActivityWithdrawBinding
import com.whdx.home.ui.dialog.WithdrawDialog
import com.whdx.home.util.ConfigHolder
import com.whdx.home.vm.MyWalletViewModel
import kotlinx.android.synthetic.main.activity_withdraw.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WithdrawActivity : BaseBindingActivity<MyWalletViewModel, ActivityWithdrawBinding>() {

    val withdrawDialog: WithdrawDialog by lazy { WithdrawDialog(this) }
    override fun initVM(): MyWalletViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.success.observe(this, Observer {
            if (it) {
                withdrawDialog?.dismiss()
                WithdrawSuccessActivity.start(this)
                finish()
            }
        })
        mViewModel.mBalanceLive.observe(this, Observer {
            cvv_top.setTopStrLittle(
                "≈ " + it.balance.divide(it.btw_price).stripTrailingZeros()
                    .toPlainString() + " BIW"
            )
        })
    }

    override fun setLayoutId() = R.layout.activity_withdraw

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.vm = mViewModel
        titleBar.setOnLeftClickListener { finish() }
        tvSure.clickWithTrigger {
            val address = etAddress.text.toString().trim()
            val amount = etAmount.text.toString().trim()
            if (address.isNullOrEmpty()) {
                SmartToast.show(getString(R.string.input_withdraw_address))
                return@clickWithTrigger
            }
            if (amount.isNullOrEmpty()) {
                SmartToast.show(getString(R.string.input_withdraw_amount))
                return@clickWithTrigger
            }
            withdrawDialog.setOnTextListener {
                if (!ConfigHolder.isCorrectPassword(it)) {
                    SmartToast.error(getString(R.string.passwrod_error))
                    return@setOnTextListener
                }
                mViewModel.requestWithdraw(address, amount)

            }
            withdrawDialog.show()
        }
    }

    override fun initData() {
        mViewModel.getMyBalance()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, WithdrawActivity::class.java))
        }
    }
}