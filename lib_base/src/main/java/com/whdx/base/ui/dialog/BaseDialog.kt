package com.whdx.base.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.jetbrains.anko.padding

abstract class BaseDialog(context: Context) : Dialog(context), LifecycleOwner {
    lateinit var lifecycleRegistry:LifecycleRegistry
    init {
        lifecycleRegistry = LifecycleRegistry(this)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry.currentState = (Lifecycle.State.CREATED)
    }

    override fun getLifecycle(): Lifecycle= lifecycleRegistry

    open fun configAttrs(attributes: WindowManager.LayoutParams?, it: Window) {

    }

    override fun dismiss() {
        super.dismiss()
        lifecycleRegistry.currentState = (Lifecycle.State.DESTROYED);
    }
}