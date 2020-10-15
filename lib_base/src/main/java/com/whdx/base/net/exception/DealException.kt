package com.whdx.base.net.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.whdx.base.R
import com.whdx.base.app.BaseApplication

import org.json.JSONException
import retrofit2.HttpException

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException


object DealException {

    fun handlerException(t: Throwable): ResultException {
        val ex: ResultException
        if (t is ResultException) {
            ex = t
        } else if (t is HttpException) {
            ex = when (t.code()) {
                ApiResultCode.UNAUTHORIZED,
                ApiResultCode.FORBIDDEN, //权限错误
                ApiResultCode.NOT_FOUND -> ResultException(
                    t.code().toString(),
                    "网络错误"
                )
                ApiResultCode.REQUEST_TIMEOUT,
                ApiResultCode.GATEWAY_TIMEOUT -> ResultException(
                    t.code().toString(),
                    BaseApplication.CONTEXT.getString(R.string.network_timeout)
                )
                ApiResultCode.INTERNAL_SERVER_ERROR,
                ApiResultCode.BAD_GATEWAY,
                ApiResultCode.SERVICE_UNAVAILABLE -> ResultException(
                    t.code().toString(),
                    BaseApplication.CONTEXT.getString(R.string.server_error)
                )
                else -> ResultException(t.code().toString(), "网络错误")
            }
        } else if (t is JsonParseException
            || t is JSONException
            || t is ParseException
        ) {
            ex = ResultException(
                ApiResultCode.PARSE_ERROR,
                "解析错误"
            )
        } else if (t is SocketException) {
            ex = ResultException(
                ApiResultCode.REQUEST_TIMEOUT.toString(),
                BaseApplication.CONTEXT.getString(R.string.network_error)
            )
        } else if (t is SocketTimeoutException) {
            ex = ResultException(
                ApiResultCode.REQUEST_TIMEOUT.toString(),
                BaseApplication.CONTEXT.getString(R.string.net_timeout)
            )
        } else if (t is SSLHandshakeException) {
            ex = ResultException(
                ApiResultCode.SSL_ERROR,
                "证书验证失败"
            )
            return ex
        } else if (t is UnknownHostException) {
            ex = ResultException(
                ApiResultCode.UNKNOW_HOST,
                BaseApplication.CONTEXT.getString(R.string.tip_network_error)
            )
            return ex
        } else if (t is UnknownServiceException) {
            ex = ResultException(
                ApiResultCode.UNKNOW_HOST,
                BaseApplication.CONTEXT.getString(R.string.tip_network_error)
            )
        } else if (t is NumberFormatException) {
            ex = ResultException(
                ApiResultCode.UNKNOW_HOST,
                "数字格式化异常"
            )
        } else {
            ex = ResultException(
                ApiResultCode.UNKNOWN,
                "未知错误"
            )
            ex.printStackTrace()
        }
        return ex
    }
}