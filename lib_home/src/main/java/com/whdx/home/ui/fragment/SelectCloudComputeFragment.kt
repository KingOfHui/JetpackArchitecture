package com.whdx.home.ui.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.fragment_select_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SelectCloudComputeFragment : BaseBindingFragment<SelectCloudViewModel, ViewDataBinding>() {
    lateinit var adapter: BaseQuickAdapter<String, BaseViewHolder>;
    override fun initVM(): SelectCloudViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_select_cloud;

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text) {

            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tv, item)
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