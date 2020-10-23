package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.jeremyliao.liveeventbus.LiveEventBus
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.REFRESH_BALANCE
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyWalletBinding
import com.whdx.home.ui.activity.DepositAddressActivity
import com.whdx.home.ui.activity.WithdrawActivity
import com.whdx.home.vm.MyWalletViewModel
import kotlinx.android.synthetic.main.fragment_my_wallet.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import java.math.BigDecimal

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 16:33
 */
class MyWalletFragment : BaseBindingFragment<MyWalletViewModel, FragmentMyWalletBinding>() {

    override fun initVM(): MyWalletViewModel = getViewModel()

    override fun startObserve() {
        LiveEventBus.get(REFRESH_BALANCE).observe(viewLifecycleOwner, Observer {
            mViewModel.getMyBalance()
        })
        mViewModel.mBalanceLive.observe(viewLifecycleOwner, Observer {
            cvv_top.setTopStrLittle("≈ " + it.balance.divide(it.btw_price,2,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString() + " BIW")
        })
    }

    override fun setLayoutResId() = R.layout.fragment_my_wallet

    override fun initView() {
        mDataBinding.vm = mViewModel
        viewPager.adapter = SimpleFragmentStateAdapter(
            requireActivity(),
            listOf(RecordChongZhiFragment(), RecordWithdrawFragment())
        )
        val titleArr = arrayOf(
            requireContext().getString(R.string.depositRecord),
            requireContext().getString(R.string.withdrawRecord)
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleArr[position]
        }.attach()
        tv_chongzhi.clickWithTrigger {
            DepositAddressActivity.start(requireContext())
        }
        tv_withdraw.clickWithTrigger {
            WithdrawActivity.start(requireContext())
        }
    }

    override fun initData() {
        mViewModel.getMyBalance()
    }

//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            mViewModel.getMyBalance()
//        }
//    }
}