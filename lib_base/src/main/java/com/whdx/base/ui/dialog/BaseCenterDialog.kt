package com.whdx.base.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.wjx.android.weather.common.util.DisplayUtil
import org.jetbrains.anko.wrapContent

open class BaseCenterDialog(context: Context) : BaseDialog(context) {
    override fun configAttrs(params: WindowManager.LayoutParams?, it: Window) {
        super.configAttrs(params, it)
        it.setLayout((DisplayUtil.getScreenWidth(context)*3/4).toInt(),wrapContent)
        params?.gravity = Gravity.CENTER
    }

}