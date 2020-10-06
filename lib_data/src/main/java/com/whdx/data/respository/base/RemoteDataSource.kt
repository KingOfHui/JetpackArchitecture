package com.whdx.data.respository.base

import androidx.lifecycle.asLiveData
import com.whdx.base.util.bitcoinj.BitcoinjKit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

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

    suspend fun getNetData()= safeApiCall { call(teacherService.getNetData(getHeaderMap())) }

}
