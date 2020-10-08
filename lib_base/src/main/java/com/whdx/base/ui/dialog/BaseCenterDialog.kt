package com.whdx.base.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.Window
import android.view.WindowManager

open class BaseCenterDialog(context: Context) : BaseDialog(context) {
    override fun configAttrs(params: WindowManager.LayoutParams?, it: Window) {
        super.configAttrs(params, it)
        params?.gravity = Gravity.CENTER
    }

}