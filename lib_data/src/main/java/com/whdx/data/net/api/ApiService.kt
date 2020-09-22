package com.wwy.android.data.api

import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.user.TokenBean
import com.whdx.data.data.user.User
import retrofit2.http.*


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
interface ApiService {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("padapi/student/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<User>

}