package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.home.R
import com.whdx.home.databinding.FragmentChongZhiBinding
import com.whdx.home.vm.DepositAddressViewModel
import com.whdx.home.vm.MyWalletViewModel
import kotlinx.android.synthetic.main.fragment_chong_zhi.*
import org.jetbrains.anko.imageBitmap
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/10 0010 13:13
 */
class DepositAddressActivity:BaseBindingActivity<MyWalletViewModel,FragmentChongZhiBinding>() {
    override fun initVM(): MyWalletViewModel=getViewModel()

    override fun startObserve() {
        mViewModel.mAddressBitmapLive.observe(this, Observer {
            im_qr_code.imageBitmap = it
        })
    }

    override fun setLayoutId()= R.layout.fragment_chong_zhi

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.vm = mViewModel
        tool_bar.setOnLeftClickListener { finish() }
    }

    override fun initData() {
        mViewModel.getDepositAddress()
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,DepositAddressActivity::class.java))
        }
    }
}