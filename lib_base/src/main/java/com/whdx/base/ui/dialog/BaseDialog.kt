package com.whdx.base.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import org.jetbrains.anko.padding

abstract class BaseDialog(context: Context) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.let {
            setCanceledOnTouchOutside(true)
            val attributes = it.attributes
            attributes.gravity = Gravity.CENTER
            it.attributes = attributes
            it.decorView.padding = 0
            it.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            configAttrs(attributes, it)
        }
    }

    private fun configAttrs(attributes: WindowManager.LayoutParams?, it: Window) {

    }
}