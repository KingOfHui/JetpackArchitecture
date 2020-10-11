package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.wallet.WithdrawRecordItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentRecordWithdrawBinding
import com.whdx.home.databinding.ItemRecordWithdrawBinding
import com.whdx.home.vm.RecordWithdrawViewModel
import kotlinx.android.synthetic.main.fragment_record_withdraw.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 17:16
 */
class RecordWithdrawFragment:BaseBindingFragment<RecordWithdrawViewModel,FragmentRecordWithdrawBinding>() {

    lateinit var mAdapter:BaseQuickAdapter<WithdrawRecordItem, BaseDataBindingHolder<ItemRecordWithdrawBinding>>
    override fun initVM(): RecordWithdrawViewModel= getViewModel()

    override fun startObserve() {
        mViewModel.mWithdrawRecordLive.observe(viewLifecycleOwner, Observer {
                mAdapter.setList(it)
        })
    }

    override fun setLayoutResId() = R.layout.fragment_record_withdraw

    override fun initView() {
        mDataBinding.vm = mViewModel
        mAdapter = object :
            BaseQuickAdapter<WithdrawRecordItem, BaseDataBindingHolder<ItemRecordWithdrawBinding>>(R.layout.item_record_withdraw) {
            override fun convert(
                holder: BaseDataBindingHolder<ItemRecordWithdrawBinding>,
                item: WithdrawRecordItem
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }
            }
        }
        rvRecord.adapter = mAdapter
    }

    override fun initData() {
        mViewModel.refresh()
    }
}