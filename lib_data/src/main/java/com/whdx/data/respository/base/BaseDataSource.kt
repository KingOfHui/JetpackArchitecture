package com.whdx.data.respository.base

import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.base.ResultData
import com.wwy.android.data.api.ApiService
import com.wwy.android.data.api.RetrofitClient
import com.whdx.data.net.WHDX_STUDENT
import com.whdx.data.net.WHDX_TEACHER
import com.whdx.base.net.exception.DealException
import com.whdx.base.net.exception.ResultException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/20 0019 23:34
 */
abstract class BaseDataSource {
    protected val teacherService: ApiService by lazy {
        RetrofitClient(WHDX_TEACHER).service
    }
    protected val studentService: ApiService by lazy {
        RetrofitClient(WHDX_STUDENT).service
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> ResultData<T>): ResultData<T> {
        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            SmartToast.error(DealException.handlerException(e).msg)
            ResultData.Error(DealException.handlerException(e))
        }
    }

    suspend fun <T : Any> call(
        response: BaseResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): ResultData<T> {
        return coroutineScope {
            if (response.code == 0) {
                successBlock?.let { it() }
                ResultData.Success(response.data)
            } else {
                if (response.code == 400) {
                    return@coroutineScope ResultData.Error(VersionEmptyException())
                }
                errorBlock?.let { it() }
                SmartToast.error(response.msg)
                ResultData.Error(ResultException(
                    response.code.toString(),
                    response.msg
                ))
            }
        }
    }
    class VersionEmptyException:java.lang.Exception()
}