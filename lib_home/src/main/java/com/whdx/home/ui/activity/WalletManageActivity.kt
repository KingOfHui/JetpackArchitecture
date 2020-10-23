package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.data.data.wallet.WalletModel
import com.whdx.home.R
import com.whdx.home.databinding.ItemWalletManageBinding
import com.whdx.home.vm.WalletManageViewModel
import kotlinx.android.synthetic.main.activity_wallet_manage.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WalletManageActivity : BaseBindingActivity<WalletManageViewModel, ViewDataBinding>() {

    lateinit var mAdapter: BaseQuickAdapter<WalletModel, BaseDataBindingHolder<ItemWalletManageBinding>>

    override fun initVM(): WalletManageViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mWalletListLive.observe(this, Observer {
            mAdapter?.setList(it)
        })
    }

    override fun setLayoutId() = R.layout.activity_wallet_manage

    override fun initView(savedInstanceState: Bundle?) {
        mAdapter = object :
            BaseQuickAdapter<WalletModel, BaseDataBindingHolder<ItemWalletManageBinding>>(R.layout.item_wallet_manage) {
            override fun convert(
                holder: BaseDataBindingHolder<ItemWalletManageBinding>,
                item: WalletModel
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }
            }

        }
        rvWallets.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            WalletDetailActivity.start(this,mAdapter.data[position])
        }
    }

    override fun initData() {
        mViewModel.getAllWallet()
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, WalletManageActivity::class.java))
        }
    }
}