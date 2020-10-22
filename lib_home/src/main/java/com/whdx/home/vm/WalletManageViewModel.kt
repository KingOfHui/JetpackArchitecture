package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.wallet.WalletModel
import com.whdx.data.respository.base.LocalDataSource
import kotlinx.coroutines.flow.toList

class WalletManageViewModel(private val localDataSource: LocalDataSource):BaseViewModel() {
    val mWalletListLive = MutableLiveData<List<WalletModel>>()

    fun getAllWallet() {
        launchUI {
            val loadAllWallet = localDataSource.walletDao.loadAllWallet()
            mWalletListLive.value = loadAllWallet
        }
    }
}