package com.whdx.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.whdx.data.data.user.User
import com.whdx.data.data.wallet.WalletModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(walletModel: WalletModel)

    //获取全部钱包
    @Transaction
    @Query("SELECT * FROM WalletModel")
    fun loadAllWallet(): Flow<List<WalletModel>>

    @Transaction
    @Update
    suspend fun updateWallet(vararg walletModel: WalletModel?)

    @Delete
    @Transaction
    suspend fun deleteWallet(walletModel: WalletModel)

    @Transaction
    @Query("DELETE FROM WalletModel")
    suspend fun deleteAllWallet()

    @Transaction
    @Query("UPDATE WalletModel SET currentSelect = 0")
    suspend fun updateAllUnSelected()

    @Transaction
    @Query("Select * FROM WalletModel WHERE currentSelect =1")
    fun getCurrent():List<WalletModel>
}