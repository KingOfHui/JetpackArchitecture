package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseViewModel
import com.whdx.data.data.NetData
import com.whdx.data.data.base.ResultData
import com.whdx.data.respository.UserRepository

class NetDataViewModel(private val userRepository: UserRepository):BaseViewModel() {


    val mNetDataLive: MutableLiveData<NetData> = MutableLiveData()
    fun getNetData() {
        launchUI {
            val netData = userRepository.getNetData()
            if (netData is ResultData.Success) {
                mNetDataLive.value = netData.data
            }else if(netData is ResultData.Error){
                doneError(netData.exception.message?:"")
            }
        }
    }

}