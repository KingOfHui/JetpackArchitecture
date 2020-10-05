package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.wallet.BtcDo
import com.whdx.data.data.wallet.WalletModel
import com.whdx.data.db.AppDatabase
import com.whdx.data.db.LocalDataSource

class WalletViewModel(private val localDataSource: LocalDataSource) : BaseViewModel() {
    val walletModel: MutableLiveData<WalletModel> = MutableLiveData<WalletModel>()
        get() {
            field.value ?: (WalletModel().also { field.value = it })
            return field
        }

    val mBtcDo: MutableLiveData<BtcDo> = MutableLiveData()
    val mMnemonic: MutableLiveData<List<String>> = MutableLiveData()

    fun insertWallet() {
        launchUI {
            walletModel.value?.let {
                it.address = mBtcDo.value?.address
                it.keyStoreVal = mBtcDo.value?.keyStoreVal
                it.privateKey = mBtcDo.value?.privateKey
                it.publicKey = mBtcDo.value?.publicKey
                it.currentSelect = 1
                it.importType = 0
                localDataSource.walletDao.updateAllUnSelected()
                localDataSource.walletDao.insertWallet(it)
            }
        }
    }

    fun loadAll() {
        launchUI {
            val loadAllWallet = localDataSource.walletDao.loadAllWallet()

            SmartToast.showInCenter(loadAllWallet?.toString())
        }
    }
}