package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.ImageView
import com.whdx.base.ui.dialog.BaseBottomDialog
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.hideSoftInput
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_withdraw.*

class WithdrawDialog(context: Context) :BaseBottomDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_withdraw)
        titleBar.apply {
            setOnLeftClickListener { dismiss() }
            titleBar.findViewById<ImageView>(R.id.ivRightNavigationBarOne).clickWithTrigger { dismiss() }
        }
        isClickEnable(false)
        etPWD.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                isClickEnable (p0.toString().isNotEmpty())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        cbPwdStatus.setOnCheckedChangeListener { button, b ->
            etPWD.inputType =
                if (b) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            etPWD.setSelection(etPWD.text.length)

        }
        tvSure.clickWithTrigger {
            etPWD.hideSoftInput()
            listener(etPWD.text.toString())
        }
    }

    fun isClickEnable(enable: Boolean) {
        tvSure.isEnabled = enable;
        tvSure.isClickable = enable
    }

    lateinit var listener:(String)->Unit
    fun setOnTextListener(listener: (String)->Unit) {
        this.listener= listener
    }
}