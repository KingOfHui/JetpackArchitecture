package com.whdx.home.ui

import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.MineViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMineBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class MineFragment:BaseBindingFragment<MineViewModel, FragmentMineBinding>() {
    override fun initVM(): MineViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_mine;

    override fun initView() {
    }

    override fun initData() {

    }
}