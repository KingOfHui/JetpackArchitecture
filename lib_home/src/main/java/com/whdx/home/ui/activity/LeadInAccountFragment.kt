package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.whdx.base.adapter.SimpleFragmentStateAdapter
import com.whdx.base.ui.fragment.BaseFragment
import com.whdx.home.R
import com.whdx.home.ui.fragment.LeadPrivateKeyFragment
import com.whdx.home.ui.fragment.MyCloudComputeFragment
import com.whdx.home.ui.fragment.LeadZJCFragment
import kotlinx.android.synthetic.main.fragment_account_leadin.*
import kotlinx.android.synthetic.main.fragment_account_leadin.titleBar
import kotlinx.android.synthetic.main.fragment_wallet_zjc.*

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 10:10
 */
class LeadInAccountFragment : BaseFragment() {
    override fun setLayoutResId() = R.layout.fragment_account_leadin

    override fun initView() {
        titleBar.setOnLeftClickListener { Navigation.findNavController(titleBar).navigateUp() }

        viewPager.adapter = SimpleFragmentStateAdapter(
            requireActivity(), listOf(
                LeadZJCFragment(),
                LeadPrivateKeyFragment()
            )
        )
        var current = 0
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioZjc -> current = 0
                R.id.radioPrivateKey -> current = 1
            }
            viewPager.currentItem = current
        }
        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0->radioGroup.check(R.id.radioZjc)
                    1->radioGroup.check(R.id.radioPrivateKey)
                }
            }
        })
        viewPager.currentItem = current
    }

    override fun initData() {
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,LeadInAccountFragment::class.java))
        }
    }
}