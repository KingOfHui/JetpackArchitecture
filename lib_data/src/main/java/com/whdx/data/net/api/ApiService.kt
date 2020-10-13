package com.wwy.android.data.api

import com.whdx.data.data.MyStorage
import com.whdx.data.data.NetData
import com.whdx.data.data.Rank
import com.whdx.data.data.UpdateVersion
import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.product.InvestProduct
import com.whdx.data.data.product.Product
import com.whdx.data.data.topic.Topic
import com.whdx.data.data.user.*
import com.whdx.data.data.wallet.*
import okhttp3.RequestBody
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

    @GET("v1/invest/myStorage")
    suspend fun getMyStorage(@HeaderMap headers: Map<String, String>): BaseResponse<MyStorage>

    @GET("v1/invest/list")
    suspend fun getInvestList(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<InvestProduct>

    @GET("v1/invest/bonusRank")
    suspend fun getBonusRank(
        @HeaderMap headers: Map<String, String>
    ): BaseResponse<List<Rank>>

    @GET("v1/wallet/usdtBalance")
    suspend fun getUSDTBalance(@HeaderMap headers: Map<String, String>): BaseResponse<USDTBalance>


    @POST("v1/invest/lease")
    suspend fun requestInvestLease(
        @HeaderMap headers: Map<String, String>,
        @Body requestBody: RequestBody
//        @Field("prod_id") prod_id: String,
//        @Field("quantity") quantity: String
    ): BaseResponse<Any>

    @POST("v1/user/inviteData")
    suspend fun requestInviteData(@HeaderMap headers: Map<String, String>): BaseResponse<InviteData>

    @GET("v1/user/inviteList")
    suspend fun getInviteList(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<InviteListResponse>

    @GET("v1/wallet/getDepositAddress/USDT")
    suspend fun getDepositAddress(@HeaderMap headers: Map<String, String>): BaseResponse<String>

    @GET("v1/user/info")
    suspend fun getUserInfo(@HeaderMap headers: Map<String, String>): BaseResponse<UserInfo>

    @GET("v1/wallet/depositList")
    suspend fun getDepositList(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<DepositRecord>

    @GET("v1/wallet/withdrawList")
    suspend fun getWithdrawList(
        @HeaderMap headers: Map<String, String>,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<WithdrawRecord>

    @GET("v1/invest/bonusList")
    suspend fun getInvestBonusList(
        @HeaderMap headers: Map<String, String>, @Query("page") page: Int,
        @Query("limit") limit: Int, @Query("investId") investId: Int
    ): BaseResponse<InvestBonus>

    @GET("v1/invest/memberBonusList")
    suspend fun getTotalBonusList(
        @HeaderMap headers: Map<String, String>, @Query("page") page: Int,
        @Query("limit") limit: Int
    ): BaseResponse<MemberBonusList>


    @POST("v1/wallet/withdraw")
    suspend fun requestWithdraw(
        @HeaderMap headers: Map<String, String>,
        @Body requestBody: RequestBody
    ): BaseResponse<Any>

    @POST("v1/user/openBid")
    suspend fun openBid(
        @HeaderMap headers: Map<String, String>,
        @Body requestBody: RequestBody
    ): BaseResponse<Any>

    @GET("v1/getAppOnline")
    suspend fun getAppOnline(@Query("version") version: String): BaseResponse<UpdateVersion>
}