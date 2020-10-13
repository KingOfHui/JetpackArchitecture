package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import com.whdx.base.ui.dialog.BaseCenterDialog
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_update.*

class UpdateDialog(context: Context,val listener: () -> Unit) : BaseCenterDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_update)
        tvCancel.clickWithTrigger { dismiss() }
        tvUpdate.clickWithTrigger { listener() }

    }
}