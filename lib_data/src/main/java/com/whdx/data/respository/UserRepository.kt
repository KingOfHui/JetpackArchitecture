package com.whdx.data.respository

import com.whdx.base.reponsitory.BaseRepository
import com.whdx.data.data.MyStorage
import com.whdx.data.data.NetData
import com.whdx.data.data.Rank
import com.whdx.data.data.base.BaseResponse
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.InvestProduct
import com.whdx.data.data.product.Product
import com.whdx.data.data.topic.Topic
import com.whdx.data.data.user.InviteData
import com.whdx.data.data.user.InviteListItem
import com.whdx.data.data.user.InviteListResponse
import com.whdx.data.data.user.User
import com.whdx.data.data.wallet.USDTBalance
import com.whdx.data.respository.base.RemoteDataSource

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0020 23:23
 */
class UserRepository(private val dataSource: RemoteDataSource) : BaseRepository() {

    suspend fun login(id: String, pwd: String): ResultData<User> {
        return dataSource.login(id, pwd)
    }

    suspend fun getProductList(page: Int, limit: Int): ResultData<Product> {
        return dataSource.getProduceList(page, limit)
    }
    suspend fun getInviteList(page: Int, limit: Int): ResultData<InviteListResponse> {
        return dataSource.getInviteList(page, limit)
    }

    suspend fun getTopic(): ResultData<List<Topic>> {
        return dataSource.getTopic()
    }

    suspend fun getNetData(): ResultData<NetData> {
        return dataSource.getNetData()
    }

    suspend fun getMyStorage(): ResultData<MyStorage> {
        return dataSource.getMyStorage()
    }

    suspend fun getInvestList(page: Int, limit: Int): ResultData<InvestProduct> {
        return dataSource.getInvestList(page, limit)
    }

    suspend fun getBonusRank(): ResultData<List<Rank>> {
        return dataSource.getBonusRank()
    }

    suspend fun getUSDTBalance(): ResultData<USDTBalance> {
        return dataSource.getUSDTBalance()
    }

    suspend fun requestInvestLease(pro_id: String, quantity: String): ResultData<Any> {
        return dataSource.requestInvestLease(pro_id, quantity)
    }

    suspend fun requestInviteData(): ResultData<InviteData> {
        return dataSource.requestInviteData()
    }

    suspend fun getDepositAddress(): BaseResponse<Any> {
        return dataSource.getDepositAddress()
    }

    suspend fun getDepositList(page: Int,limit: Int) = dataSource.getDepositList(page, limit)
    suspend fun getInvestBonusList(page: Int,limit: Int,investId:Int) = dataSource.getInvestBonusList(page, limit, investId)
}