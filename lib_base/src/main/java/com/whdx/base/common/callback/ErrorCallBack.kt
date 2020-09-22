package com.whdx.base.common.callback

import android.content.Context
import android.view.View
import android.widget.TextView
import com.whdx.base.R
import com.kingja.loadsir.callback.Callback
import org.jetbrains.anko.find


class ErrorCallBack : Callback() {
    override fun onCreateView(): Int = R.layout.layout_error

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        view?.find<TextView>(R.id.tv_error)?.setOnClickListener {

        }
        return super.onReloadEvent(context, view)
    }

}