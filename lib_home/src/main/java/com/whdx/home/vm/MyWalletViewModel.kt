package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.base.ResultData
import com.whdx.data.data.wallet.USDTBalance
import com.whdx.data.respository.UserRepository

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 16:34
 */
class MyWalletViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<String>>() {

    val mBalanceLive: MutableLiveData<USDTBalance> = MutableLiveData()

    val mAddressLive: MutableLiveData<String> = MutableLiveData()

    fun getDepositAddress() {
        launchUI {
            val depositAddress = userRepository.getDepositAddress()
            if (depositAddress.code == 0) {
                mAddressLive.value = depositAddress.sign
            } else{
                SmartToast.showInCenter("获取地址失败")
            }
        }
    }

    fun getMyBalance() {
        launchUI {
            val usdtBalance = userRepository.getUSDTBalance()
            if (usdtBalance is ResultData.Success) {
                mBalanceLive.value = usdtBalance.data
            }
            getDepositAddress()
        }
    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {

    }
}