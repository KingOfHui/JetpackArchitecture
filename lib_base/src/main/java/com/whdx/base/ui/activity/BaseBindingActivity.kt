package com.whdx.base.ui.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.whdx.base.vm.BaseViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/21 0021 9:00
 */
abstract class BaseBindingActivity<VM:BaseViewModel,DB:ViewDataBinding> : BaseVMActivity<VM>() {

    lateinit var mDataBinding: DB

    override fun setCreateLayout() {
        mDataBinding = DataBindingUtil.setContentView(this, setLayoutId())
        mDataBinding.lifecycleOwner = this
    }
}