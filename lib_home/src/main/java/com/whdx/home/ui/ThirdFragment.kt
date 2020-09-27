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
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class ThirdFragment:BaseBindingFragment<HomeViewModel, FragmentMineBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId() = R.layout.fragment_third;

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
        Timber.tag("dhdhdh").e("initData ThirdFragment")
        /*lifecycleScope.launch {
            mViewModel.login("9241885", "111111")

        }*/
    }
}