package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.ui.fragment.BaseVMFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyWalletBinding
import com.whdx.home.vm.MyWalletViewModel
import kotlinx.android.synthetic.main.fragment_my_wallet.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 16:33
 */
class MyWalletFragment:BaseBindingFragment<MyWalletViewModel,FragmentMyWalletBinding>() {

    private val titleArr = arrayOf("充值记录","提现记录")
    override fun initVM(): MyWalletViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_my_wallet

    override fun initView() {
        viewPager.adapter = SimpleFragmentStateAdapter(requireActivity(), listOf(RecordChongZhiFragment(),RecordWithdrawFragment()))
        TabLayoutMediator(tabLayout,viewPager) { tab, position -> tab.text = titleArr[position] }.attach()

    }

    override fun initData() {
    }
}