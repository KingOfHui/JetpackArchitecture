package com.whdx.data.db

import com.whdx.data.respository.base.BaseDataSource

class LocalDataSource : BaseDataSource() {
    val walletDao:WalletDao by lazy {
        AppDatabase.getInstance().walletDao()
    }
}