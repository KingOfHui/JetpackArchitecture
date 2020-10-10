package com.whdx.home.ui.dialog

import android.content.Context
import android.os.Bundle
import com.whdx.base.ui.dialog.BaseCenterDialog
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import kotlinx.android.synthetic.main.dialog_no_open_bid.*

class WarningDialog(context: Context) : BaseCenterDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_no_open_bid)
        tvToOpen.clickWithTrigger {
            listener()
            dismiss()
        }
    }

    lateinit var listener:()->Unit
    fun setOnClickListener(listener: () -> Unit) {
        this.listener = listener
    }
}