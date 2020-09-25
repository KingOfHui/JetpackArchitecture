package com.wwy.android.data.api

import com.whdx.base.util.ext.CookieClass.cookieJar
import com.whdx.data.net.WHDX_TEACHER
import com.whdx.data.net.getHost
import com.whdx.base.net.interceptor.CommonInterceptor
import okhttp3.OkHttpClient

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
class RetrofitClient(val hostType: Int = WHDX_TEACHER) : BaseRetrofitClient() {
    val service by lazy { getService(ApiService::class.java) }

    override fun getBaseUrl(): String {
        return getHost(hostType)
    }

    //okhttp 扩展
    override fun handleBuilder(builder: OkHttpClient.Builder) {
        builder.addInterceptor(CommonInterceptor())
        builder.cookieJar(cookieJar)
    }

}