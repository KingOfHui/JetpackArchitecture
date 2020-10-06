package com.whdx.data.respository

import com.whdx.base.reponsitory.BaseRepository
import com.whdx.data.data.NetData
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.product.Product
import com.whdx.data.data.topic.Topic
import com.whdx.data.data.user.User
import com.whdx.data.respository.base.RemoteDataSource

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0020 23:23
 */
class UserRepository(private val dataSource: RemoteDataSource) :BaseRepository(){

    suspend fun login(id: String, pwd: String): ResultData<User> {
       return dataSource.login(id, pwd)
    }

    suspend fun getProductList(page: Int, limit: Int): ResultData<Product> {
        return dataSource.getProduceList(page,limit)
    }

    suspend fun getTopic(): ResultData<List<Topic>> {
        return dataSource.getTopic()
    }

    suspend fun getNetData(): ResultData<NetData> {
        return dataSource.getNetData()
    }
}