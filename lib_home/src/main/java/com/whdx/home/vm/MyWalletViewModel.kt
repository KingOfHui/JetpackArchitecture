package com.whdx.home.vm

import android.content.ClipboardManager
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.app.BaseApplication
import com.whdx.base.util.QrCodeUtil
import com.whdx.base.util.ext.clickToCopy
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
    val mAddressBitmapLive: MutableLiveData<Bitmap> = MutableLiveData()

    fun getDepositAddress() {
        launchUI {
            val depositAddress = userRepository.getDepositAddress()
            if (depositAddress.code == 0) {
                mAddressLive.value = depositAddress.data
                mAddressBitmapLive.value =
                    QrCodeUtil.createQRCode(depositAddress.data, 200, 200, null)
            } else {
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

    fun copy() {
        mAddressLive.value?.let {
            it.clickToCopy(BaseApplication.CONTEXT)
        }
    }

    fun saveBitmap() {
        mAddressBitmapLive.value?.let {
            QrCodeUtil.saveImage(it)
        }
    }

    fun requestWithdraw(address: String, amount: String) {
        launchUI {
            val requestWithdraw = userRepository.requestWithdraw(address, amount)
            if (requestWithdraw is ResultData.Success) {
                SmartToast.show("提现成功")
                getMyBalance()
            }
        }
    }

    override suspend fun load(isClear: Boolean, pageNum: Int) {

    }
}