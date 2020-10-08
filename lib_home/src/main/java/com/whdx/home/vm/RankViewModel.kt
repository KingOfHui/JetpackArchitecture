package com.whdx.home.vm

import androidx.lifecycle.MutableLiveData
import com.whdx.base.vm.BaseLoadMoreViewModel
import com.whdx.data.data.Rank
import com.whdx.data.data.base.ResultData
import com.whdx.data.respository.UserRepository

class RankViewModel(private val userRepository: UserRepository) :
    BaseLoadMoreViewModel<List<Rank>>() {

    val mRankList: MutableLiveData<List<Rank>> = MutableLiveData()

    override suspend fun load(isClear: Boolean, pageNum: Int) {
        val bonusRank = userRepository.getBonusRank()
        if (bonusRank is ResultData.Success) {
            mRankList.value = bonusRank.data
            notifyResultToTopViewModel(bonusRank.data)
        }
        refreshing.value = false
    }

}