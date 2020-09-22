package com.whdx.base.app.init

import android.app.Application

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/23 0023 0:02
 */
abstract class Initializer {
    abstract fun init(application: Application)
}