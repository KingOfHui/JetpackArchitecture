package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import com.whdx.base.ui.dialog.BaseBottomDialog
import com.whdx.base.ui.dialog.BaseCenterDialog
import com.whdx.base.util.ext.clickToCopy
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_invite_code.*

class InviteCodeDialog(context: Context,val text:String) : BaseCenterDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_invite_code)
        ivClose.clickWithTrigger { dismiss() }
        common_content.text = text
        tvCopy.clickWithTrigger { text.clickToCopy(context) }
    }


    companion object{
        fun show(context: Context,text: String){
            InviteCodeDialog(context,text).show()
        }
    }
}