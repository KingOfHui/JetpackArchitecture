package com.whdx.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whdx.base.app.BaseApplication
import com.whdx.data.data.user.User
import com.whdx.data.data.wallet.WalletModel
import com.wwy.android.data.db.UserDao


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
@Database(entities = [/*User::class*/WalletModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao

    abstract fun walletDao():WalletDao
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase().also { INSTANCE = it }
            }

        private fun buildDatabase() =
            Room.databaseBuilder(
                BaseApplication.CONTEXT,
                AppDatabase::class.java, "wallet.db"
            )
                .build()
    }
}