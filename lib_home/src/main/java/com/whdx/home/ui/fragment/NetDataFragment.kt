package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.user.User
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentNetDataBinding
import com.whdx.home.vm.NetDataViewModel
import com.whdx.provider.user.UserProviderWrap
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class NetDataFragment : BaseBindingFragment<NetDataViewModel, FragmentNetDataBinding>() {
    override fun initVM(): NetDataViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_net_data;

    override fun initView() {
        mDataBinding.vm = mViewModel
    }

    override fun initData() {
        mViewModel.getNetData()
        Timber.tag("dhdhdh").e("initData MineFragment")
    }

//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            mViewModel.getNetData()
//        }
//    }
}