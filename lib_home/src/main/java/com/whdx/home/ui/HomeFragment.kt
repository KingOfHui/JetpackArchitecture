package com.whdx.home.ui

import com.google.android.material.tabs.TabLayoutMediator
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentHomeBinding
import com.whdx.home.ui.fragment.MyCloudComputeFragment
import com.whdx.home.ui.fragment.SelectCloudComputeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:50
 */
class HomeFragment : BaseBindingFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun setLayoutResId() = R.layout.fragment_home;

    override fun initView() {
        val tabArr= arrayOf("精选云算力","我的云算力")


        activity?.let {
            view_pager.adapter = SimpleFragmentStateAdapter(it, listOf(SelectCloudComputeFragment(),
                MyCloudComputeFragment()
            ))
            TabLayoutMediator(tab_title,view_pager){tab, position ->
                tab.text = tabArr[position]
            }.attach()
        }

    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData HomeFragment")
        mDataBinding.vm = mViewModel
        mViewModel.login("9241885", "111111")
        mViewModel.refresh()
    }

    override fun startObserve() {
    }
}