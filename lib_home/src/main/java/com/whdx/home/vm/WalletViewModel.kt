package com.whdx.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.wallet.BtcDo
import com.whdx.data.data.wallet.WalletModel
import com.whdx.data.respository.base.LocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import org.jetbrains.anko.collections.forEachWithIndex

class WalletViewModel(private val localDataSource: LocalDataSource) : BaseViewModel() {
    private val _insertSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val walletModel: MutableLiveData<WalletModel> = MutableLiveData<WalletModel>()
        get() {
            field.value ?: (WalletModel().also { field.value = it })
            return field
        }

    val mBtcDo: MutableLiveData<BtcDo> = MutableLiveData()
    val mMnemonic: MutableLiveData<List<String>> = MutableLiveData()
    val insertSuccess: LiveData<Boolean>
        get() = _insertSuccess


    fun insertWallet() {
        doLoading("导入钱包中")
        launchUI {
            walletModel.value?.let {
                it.address = mBtcDo.value?.address
                it.keyStoreVal = mBtcDo.value?.keyStoreVal
                it.privateKey = mBtcDo.value?.privateKey
                it.publicKey = mBtcDo.value?.publicKey
                it.currentSelect = 1
                it.importType = 0
                val sb = StringBuilder()
                (mMnemonic.value ?: listOf()).apply {
                    forEachWithIndex { index, s ->
                        sb.append(if (index == this.size) s else "$s,")
                    }
                }
                it.mnemonic = sb.toString()
                localDataSource.walletDao.updateAllUnSelected()
                localDataSource.walletDao.insertWallet(it)
                doneSuccess()
                _insertSuccess.value = true
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