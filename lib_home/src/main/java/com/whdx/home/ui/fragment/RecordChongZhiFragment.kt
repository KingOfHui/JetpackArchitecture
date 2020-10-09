package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.wallet.DepositRecordItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentRecordChongBinding
import com.whdx.home.databinding.ItemRecordBinding
import com.whdx.home.vm.RecordChongZhiViewModel
import kotlinx.android.synthetic.main.fragment_record_chong.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 17:15
 */
class RecordChongZhiFragment : BaseBindingFragment<RecordChongZhiViewModel, FragmentRecordChongBinding>() {

    lateinit var mAdapter: BaseQuickAdapter<DepositRecordItem,BaseDataBindingHolder<ItemRecordBinding>>
    override fun initVM(): RecordChongZhiViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.depositRecordItemLive.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                mAdapter.setEmptyView(R.layout.layout_empty)
            } else {
                mAdapter.setList(it)
            }
        })
    }

    override fun setLayoutResId()= R.layout.fragment_record_chong

    override fun initView() {
        mDataBinding.vm = mViewModel
        mAdapter=object :BaseQuickAdapter<DepositRecordItem,BaseDataBindingHolder<ItemRecordBinding>>(R.layout.item_record){
            override fun convert(
                holder: BaseDataBindingHolder<ItemRecordBinding>,
                item: DepositRecordItem
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }
            }
        }
        rvRecord.adapter =mAdapter
    }

    override fun initData() {
        mViewModel.refresh()
    }
}