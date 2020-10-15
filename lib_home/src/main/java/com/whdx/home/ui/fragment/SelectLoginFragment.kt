package com.whdx.home.ui.fragment

import androidx.navigation.Navigation
import com.whdx.base.language.LocalLanguageUtil
import com.whdx.base.ui.fragment.BaseFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.getLanguage
import com.whdx.home.BuildConfig
import com.whdx.home.R
import com.whdx.home.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_select_login.*

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/13 0013 17:16
 */
class SelectLoginFragment:BaseFragment() {
    override fun setLayoutResId() = R.layout.fragment_select_login

    override fun initView() {
        tv_version.text = BuildConfig.VERSION_NAME
        tvCreate.clickWithTrigger {
            Navigation.findNavController(it).navigate(R.id.action_selectLoginFragment_to_navigation_create_wallet)
        }
        tvImport.clickWithTrigger {
            Navigation.findNavController(it).navigate(R.id.action_selectLoginFragment_to_leadInAccountFragment)
        }
        tvSwitch.clickWithTrigger {
            LocalLanguageUtil.switchLanguage(requireContext())
            LoginActivity.start(requireContext())
            requireActivity().finish()
            requireActivity().overridePendingTransition(0,0)
        }
        switchUI()
    }

    override fun initData() {
    }

    fun switchUI() {
        tvSwitch.text= if (getLanguage() == 1) "EN" else "CN"
    }
}