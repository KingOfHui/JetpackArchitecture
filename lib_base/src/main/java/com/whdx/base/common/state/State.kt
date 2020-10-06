package com.whdx.base.common.state

import androidx.annotation.StringRes

data class State(var code:StateType, var message: String? = null, @StringRes var tip: Int = 0)