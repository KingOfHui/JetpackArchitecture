package com.whdx.home.ui

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.HomeViewModel
import com.whdx.home.LoginActivity
import com.whdx.home.R
import com.whdx.home.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:50
 */
class HomeFragment :BaseBindingFragment<HomeViewModel, FragmentHomeBinding>(){
    override fun initVM(): HomeViewModel = requireActivity().getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_home;

    override fun initView() {
        textView.setOnClickListener { startActivity(Intent(requireContext(),LoginActivity::class.java)) }
    }

    override fun initData() {
        lifecycleScope.launch {
            delay(5000)
            mViewModel.login("9241885", "111111")

        }
    }
}