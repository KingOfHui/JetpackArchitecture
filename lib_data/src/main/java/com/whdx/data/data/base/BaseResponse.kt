package com.whdx.data.data.base

import android.text.TextUtils

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 17:34
 */
data class BaseResponse<out T>(val code: Int, val msg: String, val data: T)