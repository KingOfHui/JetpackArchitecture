package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.product.InvestProductItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyCloudBinding
import com.whdx.home.databinding.ItemCloudMineralBinding
import com.whdx.home.databinding.ItemMyCloudMineralBinding
import com.whdx.home.vm.MyCloudViewModel
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.fragment_select_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MyCloudComputeFragment : BaseBindingFragment<MyCloudViewModel,FragmentMyCloudBinding>() {
    lateinit var adapter: BaseQuickAdapter<InvestProductItem, BaseDataBindingHolder<ItemMyCloudMineralBinding>>;
    override fun initVM(): MyCloudViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mInvestList.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                adapter.setEmptyView(R.layout.layout_empty)
            } else {
                adapter.setList(it)
            }
        })
    }

    override fun setLayoutResId() = R.layout.fragment_my_cloud;

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = object :  BaseQuickAdapter<InvestProductItem, BaseDataBindingHolder<ItemMyCloudMineralBinding>>(R.layout.item_my_cloud_mineral) {

            override fun convert(holder: BaseDataBindingHolder<ItemMyCloudMineralBinding>, item: InvestProductItem) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }
            }

        }
        rv.adapter = adapter
        mDataBinding.vm = mViewModel
    }

    override fun initData() {
        mViewModel.getMyStorage()
        mViewModel.refresh()
    }
}