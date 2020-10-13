package com.whdx.data.respository.base

import com.google.gson.Gson
import com.whdx.base.util.bitcoinj.BitcoinjKit
import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.InviteData
import com.whdx.data.data.user.InviteListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 17:27
 */
class RemoteDataSource(private val localDataSource: LocalDataSource) : BaseDataSource() {

    private suspend fun getHeaderMap(): Map<String, String> {
        var value: String
        withContext(Dispatchers.IO) {
            val current = localDataSource.walletDao.getCurrent()
            current.get(0).let {
                val msg = System.currentTimeMillis().toString()
                val sign =
                    BitcoinjKit.signMessageBy58(msg, it.privateKey)
                value = "${it.address}#$msg#${sign}"
            }
        }
        return mapOf("Chain-Authentication" to value)
    }

    suspend fun login(username: String, password: String) =
        safeApiCall {
            call(teacherService.login(username, password))
        }

    suspend fun getAppOnline(version: String) =
        safeApiCall { call(teacherService.getAppOnline(version)) }

    suspend fun getProduceList(page: Int, limit: Int) =
        safeApiCall {
            call(
                teacherService.getProduceList(
                    headers = getHeaderMap(),
                    page = page,
                    limit = limit
                )
            )
        }

    suspend fun getTopic() =
        safeApiCall {
            call(teacherService.getTopic())
        }

    suspend fun getNetData() = safeApiCall { call(teacherService.getNetData(getHeaderMap())) }

    suspend fun getMyStorage() = safeApiCall { call(teacherService.getMyStorage(getHeaderMap())) }

    suspend fun getInvestList(page: Int, limit: Int) =
        safeApiCall { call(teacherService.getInvestList(getHeaderMap(), page, limit)) }

    suspend fun getBonusRank() =
        safeApiCall { call(teacherService.getBonusRank(getHeaderMap())) }

    suspend fun getUSDTBalance() =
        safeApiCall { call(teacherService.getUSDTBalance(getHeaderMap())) }

    suspend fun getUserInfo() =
        safeApiCall { call(teacherService.getUserInfo(getHeaderMap())) }

    suspend fun requestInvestLease(pro_id: String, quantity: String): ResultData<Any> {
        val json = Gson().toJson(mapOf("prod_id" to pro_id, "quantity" to quantity))
        return safeApiCall {
            call(
                teacherService.requestInvestLease(
                    getHeaderMap(), json.toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    suspend fun requestWithdraw(address: String, btw_amount: String): ResultData<Any> {
        val json = Gson().toJson(mapOf("address" to address, "btw_amount" to btw_amount))
        return safeApiCall {
            call(
                teacherService.requestWithdraw(
                    getHeaderMap(), json.toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    suspend fun openBid(invest_code: String): ResultData<Any> {
        val json = Gson().toJson(mapOf("invest_code" to invest_code))
        return safeApiCall {
            call(
                teacherService.openBid(
                    getHeaderMap(),
                    json.toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    suspend fun requestInviteData(): ResultData<InviteData> {
        return safeApiCall { call(teacherService.requestInviteData(getHeaderMap())) }
    }

    suspend fun getInviteList(page: Int, limit: Int): ResultData<InviteListResponse> {
        return safeApiCall { call(teacherService.getInviteList(getHeaderMap(), page, limit)) }
    }

    suspend fun getDepositAddress(): ResultData<String> {
        return safeApiCall {
            call(
                teacherService.getDepositAddress(getHeaderMap())
            )
        }
    }

    suspend fun getDepositList(page: Int, limit: Int) = safeApiCall {
        call(
            teacherService.getDepositList(
                getHeaderMap(),
                page,
                limit
            )
        )
    }

    suspend fun getWithdrawList(page: Int, limit: Int) = safeApiCall {
        call(
            teacherService.getWithdrawList(
                getHeaderMap(),
                page,
                limit
            )
        )
    }

    suspend fun getInvestBonusList(page: Int, limit: Int, investId: Int) =
        safeApiCall {
            call(
                teacherService.getInvestBonusList(
                    getHeaderMap(),
                    page,
                    limit,
                    investId
                )
            )
        }

    suspend fun getTotalBonusList(page: Int, limit: Int) =
        safeApiCall {
            call(
                teacherService.getTotalBonusList(
                    getHeaderMap(),
                    page,
                    limit
                )
            )
        }
}
