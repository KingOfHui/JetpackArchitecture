package com.whdx.base.common.callback

import com.whdx.base.R
import com.kingja.loadsir.callback.Callback


class LoadingCallBack : Callback() {
    override fun onCreateView(): Int = R.layout.layout_loading
}