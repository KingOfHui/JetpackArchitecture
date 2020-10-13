package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.data.data.wallet.MemberBonusItem
import com.whdx.home.R
import com.whdx.home.databinding.ActivityMyTotalBonusBinding
import com.whdx.home.databinding.ItemMyTotalBonusBinding
import com.whdx.home.vm.MyTotalBonusViewModel
import kotlinx.android.synthetic.main.activity_my_total_bonus.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MyTotalBonusActivity : BaseBindingActivity<MyTotalBonusViewModel, ActivityMyTotalBonusBinding>() {

    lateinit var mAdapter: BaseQuickAdapter<MemberBonusItem, BaseDataBindingHolder<ItemMyTotalBonusBinding>>
    override fun initVM(): MyTotalBonusViewModel = getViewModel()
    override fun setLayoutId() = R.layout.activity_my_total_bonus

    override fun startObserve() {
        mViewModel.totalBonusListLive.observe(this, Observer {
            if(mViewModel.isClearLive.value != false){
                mAdapter.data.clear()
            }
            mAdapter.addData(it)
        })

    }

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.vm = mViewModel
        titleBar.setOnLeftClickListener { finish() }
        mAdapter = object :
            BaseQuickAdapter<MemberBonusItem, BaseDataBindingHolder<ItemMyTotalBonusBinding>>(R.layout.item_my_total_bonus) {
            override fun convert(
                holder: BaseDataBindingHolder<ItemMyTotalBonusBinding>,
                item: MemberBonusItem
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }

            }

        }
        rvBonusRecord.adapter = mAdapter
    }

    override fun initData() {
        mViewModel.refresh()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MyTotalBonusActivity::class.java))
        }
    }
}