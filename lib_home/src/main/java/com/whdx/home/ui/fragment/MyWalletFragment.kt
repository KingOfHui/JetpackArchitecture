package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyWalletBinding
import com.whdx.home.ui.activity.DepositAddressActivity
import com.whdx.home.ui.dialog.WithdrawDialog
import com.whdx.home.vm.MyWalletViewModel
import kotlinx.android.synthetic.main.fragment_my_wallet.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 16:33
 */
class MyWalletFragment : BaseBindingFragment<MyWalletViewModel, FragmentMyWalletBinding>() {

    private val titleArr = arrayOf("充值记录", "提现记录")
    override fun initVM(): MyWalletViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_my_wallet

    override fun initView() {
        mDataBinding.vm = mViewModel
        viewPager.adapter = SimpleFragmentStateAdapter(
            requireActivity(),
            listOf(RecordChongZhiFragment(), RecordWithdrawFragment())
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleArr[position]
        }.attach()
        tv_chongzhi.clickWithTrigger {
            DepositAddressActivity.start(requireContext())
        }
        tv_withdraw.clickWithTrigger {
            val withdrawDialog = WithdrawDialog(requireContext())
            withdrawDialog.setOnTextListener {
                mViewModel.requestWithdraw("", it)
            }
            withdrawDialog.show()
        }
    }

    override fun initData() {
        mViewModel.getMyBalance()
    }
}