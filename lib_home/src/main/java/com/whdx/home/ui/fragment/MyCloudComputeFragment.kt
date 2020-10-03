package com.whdx.home.ui.fragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyCloudBinding
import com.whdx.home.databinding.ItemCloudMineralBinding
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.fragment_select_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MyCloudComputeFragment : BaseBindingFragment<SelectCloudViewModel,FragmentMyCloudBinding>() {
    lateinit var adapter: BaseQuickAdapter<String, BaseDataBindingHolder<ItemCloudMineralBinding>>;
    override fun initVM(): SelectCloudViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_my_cloud;

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = object :  BaseQuickAdapter<String, BaseDataBindingHolder<ItemCloudMineralBinding>>(R.layout.item_cloud_mineral) {

            override fun convert(holder: BaseDataBindingHolder<ItemCloudMineralBinding>, item: String) {
//                holder.dataBinding?.tv?.text = item
            }

        }
        rv.adapter = adapter
        val list = mutableListOf<String>()
        for (i in 0..20) {
            list.add("position $i")
        }
        adapter.setList(list)
    }

    override fun initData() {
    }
}