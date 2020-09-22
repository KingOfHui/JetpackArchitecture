package com.whdx.data.respository.base

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 17:27
 */
class RemoteDataSource : BaseDataSource() {
    suspend fun login(username: String, password: String) =
        safeApiCall {
            call(teacherService.login(username, password))
        }

    suspend fun login2(username: String, password: String) =
        teacherService.login(username, password)
}
