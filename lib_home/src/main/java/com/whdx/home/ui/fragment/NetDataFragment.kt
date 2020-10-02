package com.whdx.home.ui.fragment

import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.user.User
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentNetDataBinding
import com.whdx.provider.user.UserProviderWrap
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class NetDataFragment:BaseBindingFragment<HomeViewModel, FragmentNetDataBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_net_data;

    override fun initView() {
    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData MineFragment")
        /*lifecycleScope.launch {
            mViewModel.login("9241885", "111111")

        }*/
    }
}