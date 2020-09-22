package com.wwy.android.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.whdx.data.data.user.User

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
@Dao
interface UserDao {
    //插入所有
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User?)

    //获取User
    @Transaction
    @Query("SELECT * FROM User")
    fun loadUser(): LiveData<User>

    //获取id
    @Transaction
    @Query("SELECT user_id FROM User")
    fun loadUid(): LiveData<Int>

    //获取用户公开name
    @Transaction
    @Query("SELECT user_public_name FROM User")
    fun loadPublicName(): String

    //设置publicName
    @Transaction
    @Query("UPDATE User SET user_public_name= :publicName")
    fun setPublicName(publicName: String): Int

    //通过id设置publicName 适合多账户
    @Transaction
    @Query("UPDATE User SET user_public_name= :publicName WHERE user_id = :uid")
    fun setPublicNameByUid(publicName: String, uid: Int): Int

    //删除全部数据
    @Transaction
    @Query("DELETE FROM User")
    suspend fun deleteAll()

    //通过id删除全部数据 适合多账户
    @Transaction
    @Query("DELETE FROM User WHERE user_id = :uid")
    fun deleteAll(uid: Int)

    //更新用户信息
    @Update
    @Transaction
    fun updateUsers(vararg users: User?)
}