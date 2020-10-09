package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentRecordWithdrawBinding
import com.whdx.home.vm.MyWalletViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 17:16
 */
class RecordWithdrawFragment:BaseBindingFragment<MyWalletViewModel,FragmentRecordWithdrawBinding>() {
    override fun initVM(): MyWalletViewModel= getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_record_withdraw

    override fun initView() {
    }

    override fun initData() {
    }
}