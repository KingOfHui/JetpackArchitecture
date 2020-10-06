package com.whdx.data.respository.base

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
                errorBlock?.let { it() }
                ResultData.Error(ResultException(
                    response.code.toString(),
                    response.msg
                ))
            }
        }
    }
}