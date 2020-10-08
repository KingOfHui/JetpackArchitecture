package com.whdx.data.respository.base

import com.bumptech.glide.load.engine.cache.SafeKeyGenerator
import com.google.gson.Gson
import com.whdx.base.util.bitcoinj.BitcoinjKit
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.user.InviteData
import com.whdx.data.data.user.InviteListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
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

    suspend fun requestInviteData(): ResultData<InviteData> {
        return safeApiCall { call(teacherService.requestInviteData(getHeaderMap())) }
    }

    suspend fun getInviteList(page: Int, limit: Int): ResultData<InviteListResponse> {
        return safeApiCall { call(teacherService.getInviteList(getHeaderMap(),page, limit)) }
    }
}
