package com.wwy.android.data.api

import com.whdx.data.data.NetData
import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.product.Product
import com.whdx.data.data.topic.Topic
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

    @GET("v1/product/list")
    suspend fun getProduceList(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<Product>

    @GET("v1/announcement/list")
    suspend fun getTopic(): BaseResponse<List<Topic>>

    @GET("v1/net/data")
    suspend fun getNetData(
        @HeaderMap headers: Map<String, String>
    ): BaseResponse<NetData>
}