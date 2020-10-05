package com.whdx.data.net

import com.whdx.data.BuildConfig

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
const val WHDX_TEACHER = 1
const val WHDX_STUDENT = 2
val teacherUrl:String by lazy {
    if (BuildConfig.DEBUG) {
        "https://admin-test.bvw.im/minerManagement/"
    } else{
        "https://t.bvw.im/finance/"
    }
}
const val studentUrl = "https://www.baidu.io"

fun getHost(hostType: Int): String {
    lateinit var host: String
    when (hostType) {
        WHDX_TEACHER -> host =
            teacherUrl
        WHDX_STUDENT -> host =
            studentUrl
    }
    return host
}
