package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import android.text.InputType
import com.whdx.base.ui.dialog.BaseCenterDialog
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.hideSoftInput
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_pwd_input.*

class InputPasswordDialog(context: Context, val title: String, val editVisible: Boolean = false) :
    BaseCenterDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_pwd_input)

        common_content.inputType =
            if (editVisible) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        tvTitle.text = title
        common_cancel.clickWithTrigger {
            dismiss()
        }

        common_sure.clickWithTrigger {
            common_content.hideSoftInput()
            listener(common_content.text.trim().toString())
        }
    }

    lateinit var listener: (String) -> Unit
    fun setInputListener(listener: (String) -> Unit) {
        this.listener = listener
    }
}