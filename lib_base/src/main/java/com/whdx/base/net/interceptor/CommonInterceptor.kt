package com.whdx.base.net.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

/**
 * 公共请求拦截器
 */
class CommonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = addHeaders(request.newBuilder())
        return chain.proceed(builder)

    }

    private fun addHeaders(builder: Request.Builder): Request {
        return builder.addHeader("Content-Type", "application/json")
            .addHeader("charset", "UTF-8").build()
    }
}