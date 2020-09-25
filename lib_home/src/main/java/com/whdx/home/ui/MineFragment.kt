package com.whdx.home.ui

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.HomeViewModel
import com.whdx.home.LoginActivity
import com.whdx.home.MineViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMineBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class MineFragment:BaseBindingFragment<HomeViewModel, FragmentMineBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_mine;

    override fun initView() {
        textView.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    LoginActivity::class.java
                )
            )
        }
    }

    override fun initData() {
        lifecycleScope.launch {
            mViewModel.login("9241885", "111111")

        }
    }
}