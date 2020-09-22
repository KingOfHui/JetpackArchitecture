package com.whdx.data.respository

import com.whdx.base.reponsitory.BaseRepository
import com.whdx.data.data.base.ResultData
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
}