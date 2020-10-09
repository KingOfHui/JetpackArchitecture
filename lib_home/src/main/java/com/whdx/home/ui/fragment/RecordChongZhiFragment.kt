package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentRecordChongBinding
import com.whdx.home.vm.MyWalletViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 17:15
 */
class RecordChongZhiFragment : BaseBindingFragment<MyWalletViewModel, FragmentRecordChongBinding>() {
    override fun initVM(): MyWalletViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_record_chong

    override fun initView() {
    }

    override fun initData() {
    }
}