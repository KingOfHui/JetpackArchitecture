package com.whdx.home.ui

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.vm.BaseViewModel
import com.whdx.home.HomeViewModel
import com.whdx.home.LoginActivity
import com.whdx.home.R
import com.whdx.home.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:50
 */
class HomeFragment : BaseBindingFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    lateinit var adapter: BaseQuickAdapter<String, BaseViewHolder>;
    override fun startObserve() {
        mViewModel.mList.observe(viewLifecycleOwner,
            Observer<MutableList<String>> {
                adapter.setList(it)
            })
    }

    override fun setLayoutResId() = R.layout.fragment_home;

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text) {

            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tv, item)
            }

        }
        rv.adapter = adapter

    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData HomeFragment")
//        mDataBinding.vm = mViewModel
//        mViewModel.login("9241885", "111111")
        mViewModel.refresh()
    }
}