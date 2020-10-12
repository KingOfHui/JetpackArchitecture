package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.coder.zzq.smartshow.toast.SmartToast
import com.jeremyliao.liveeventbus.LiveEventBus
import com.whdx.base.ui.dialog.BaseBottomDialog
import com.whdx.base.util.WeakRefHandler
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.hideSoftInput
import com.whdx.base.util.wrap.CallbackNoReturn
import com.whdx.base.util.wrap.CallbackWithReturn
import com.whdx.home.R
import com.whdx.home.util.ConfigHolder
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.dialog_lease.*
import java.math.BigDecimal

class LeaseDialog(
    context: Context,
    val balance: BigDecimal = BigDecimal.ZERO,
    val amount: BigDecimal = BigDecimal.ZERO,
    val id: String = "0",
    val viewModel: SelectCloudViewModel? = null,
    val viewLifecycleOwner: LifecycleOwner
) : BaseBottomDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_lease)
        titleBar.apply {
            findViewById<ImageView>(R.id.ivBackNavigationBar).clickWithTrigger { dismiss() }
            findViewById<ImageView>(R.id.ivRightNavigationBarOne).clickWithTrigger { dismiss() }
        }
        tvAmount.text = "$amount USDT"
        tvBalance.text = String.format(context.getString(R.string.can_used_balance), balance)
        cbPwdStatus.setOnCheckedChangeListener { _, isChecked ->
            etPWD.inputType =
                if (isChecked) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            etPWD.setSelection(etPWD.text.length)
        }
        tvSure.isClickable = false
        tvSure.isEnabled = false
        etPWD.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                tvSure.isClickable =
                    p0?.toString()?.length!! > (0) && as_amount.number.multiply(amount) <= balance
                tvSure.isEnabled =
                    p0?.toString()?.length!! > (0) && as_amount.number.multiply(amount) <= balance
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        as_amount.setNumberUpdateListener(object : CallbackNoReturn<BigDecimal> {
            override fun callback(p: BigDecimal) {
                tvSure.isClickable = p.multiply(amount) <= balance && etPWD.text.toString()
                    .isNotEmpty()
                tvSure.isEnabled =
                    p.multiply(amount) <= balance && etPWD.text.toString().isNotEmpty()
                if (p.multiply(amount) > balance) {
                    SmartToast.error("输入数量大于可用余额")
                }
            }
        })
        tvSure.clickWithTrigger {
            etPWD.hideSoftInput()
            if (!ConfigHolder.isCorrectPassword(etPWD.text.toString().trim())) {
                SmartToast.error(R.string.passwrod_error)
                return@clickWithTrigger
            }
            if (as_amount.number.compareTo(BigDecimal.ZERO) > 0) {
                viewModel?.requestInvestLease(id, as_amount.number.toPlainString())
            } else {
                SmartToast.error("请输入正确数量")
            }
        }
    }

    companion object {
        fun show(
            context: Context,
            balance: BigDecimal,
            amount: BigDecimal,
            id: String,
            mViewModel: SelectCloudViewModel,
            viewLifecycleOwner: LifecycleOwner
        ):LeaseDialog {
            val leaseDialog =
                LeaseDialog(context, balance, amount, id, mViewModel, viewLifecycleOwner)
            leaseDialog.show()
            return leaseDialog
        }
    }
}