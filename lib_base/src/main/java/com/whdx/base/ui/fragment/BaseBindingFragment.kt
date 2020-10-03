package com.whdx.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.whdx.base.BR
import com.whdx.base.vm.BaseViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/23 0023 15:04
 */
abstract class BaseBindingFragment<VM: BaseViewModel,DB: ViewDataBinding> :BaseVMFragment<VM>() {
    protected lateinit var mDataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, setLayoutResId(), container, false)
        mDataBinding.lifecycleOwner = this
//        loadService = LoadSir.getDefault().register(mDataBinding.root) { reLoad() }
//        return loadService.loadLayout
        return mDataBinding.root
    }
}