package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import com.whdx.base.ui.dialog.BaseCenterDialog
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_pwd_input.*

class InputPasswordDialog(context: Context) : BaseCenterDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_pwd_input)

        common_cancel.clickWithTrigger {
            dismiss()
        }

        common_sure.clickWithTrigger {
            mListener.clickSure(common_content.text.trim().toString())
        }
    }

    lateinit var mListener: OnClickListener
    fun setListener(listener: OnClickListener){
        mListener = listener
    }

    interface OnClickListener{
        fun clickSure(pwd:String)
    }
}