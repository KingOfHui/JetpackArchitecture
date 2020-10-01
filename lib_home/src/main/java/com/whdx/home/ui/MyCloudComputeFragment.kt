package com.whdx.home.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.vm.SelectCloudViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MyCloudComputeFragment : BaseBindingFragment<SelectCloudViewModel,ViewDataBinding>() {
    override fun initVM(): SelectCloudViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_select_cloud;

    override fun initView() {
    }

    override fun initData() {
    }
}