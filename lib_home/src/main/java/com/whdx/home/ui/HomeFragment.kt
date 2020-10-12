package com.whdx.home.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.activity.CommonWebActivity
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentHomeBinding
import com.whdx.home.ui.activity.MyTotalBonusActivity
import com.whdx.home.ui.activity.RankActivity
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
        val tabArr = arrayOf(getString(R.string.select_cloud_power), getString(R.string.my_cloud_power))


        activity?.let {
            view_pager.adapter = SimpleFragmentStateAdapter(
                it, listOf(
                    SelectCloudComputeFragment(),
                    MyCloudComputeFragment()
                )
            )
            TabLayoutMediator(tab_title, view_pager) { tab, position ->
                tab.text = tabArr[position]
            }.attach()
            tab_title.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab_title.background = if (tab?.position == 0) {
                        group2.visibility = View.GONE
                        resources.getDrawable(com.whdx.base.R.mipmap.img_home_tab_bg_my)
                    } else {
                        group2.visibility = View.VISIBLE
                        resources.getDrawable(com.whdx.base.R.mipmap.img_home_tab_bg_select)
                    }
                }

            })
        }
        tab_title.background = resources.getDrawable(com.whdx.base.R.mipmap.img_home_tab_bg_my)
        tvRank.clickWithTrigger {
            RankActivity.start(requireContext())
        }
        tvBonusForward.clickWithTrigger {
            MyTotalBonusActivity.start(requireContext())
        }

    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData HomeFragment")
        mDataBinding.vm = mViewModel
        mViewModel.getTopic()
    }

    override fun startObserve() {
        mViewModel.mTopic.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                val topic = it[0]
                tvTopicTitle.text = topic.title
                tvTopicTime.text = topic.create_at
                tvTopicTitle.clickWithTrigger {
                    CommonWebActivity.start(requireContext(),topic.content,topic.title)
                }
                llTopic.isVisible = true
            } else{
                llTopic.isVisible = false
            }
        })
    }
}