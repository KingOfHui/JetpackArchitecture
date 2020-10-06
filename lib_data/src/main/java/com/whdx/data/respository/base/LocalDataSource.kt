package com.whdx.data.respository.base

import com.whdx.data.db.AppDatabase
import com.whdx.data.db.WalletDao

class LocalDataSource : BaseDataSource() {
    val walletDao: WalletDao by lazy {
        AppDatabase.getInstance().walletDao()
    }
}