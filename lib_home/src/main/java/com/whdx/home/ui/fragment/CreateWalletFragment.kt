package com.whdx.home.ui.fragment

import android.text.TextUtils
import androidx.navigation.Navigation
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.databinding.FragmentWalletCreateBinding
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_wallet_create.*
import kotlinx.android.synthetic.main.fragment_wallet_create.btnNext
import kotlinx.android.synthetic.main.fragment_wallet_create.titleBar
import kotlinx.android.synthetic.main.fragment_wallet_zjc.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CreateWalletFragment:BaseBindingFragment<WalletViewModel,FragmentWalletCreateBinding>() {
    override fun initVM(): WalletViewModel= requireActivity().getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_wallet_create

    override fun initView() {
        titleBar.setOnLeftClickListener { requireActivity().finish() }

        btnNext.setOnClickListener {
            if (!checkbox.isChecked) {
                SmartToast.showInCenter(getString(R.string.wallet_create_xieyi))
                return@setOnClickListener
            }
            val walletName = editWalletName.text.toString().trim()
            val pwd = editWalletPassword.text.toString().trim()
            val repeatPwd = editRepeatPassword.text.toString().trim()
            if (TextUtils.isEmpty(walletName)) {
                SmartToast.showInCenter(R.string.wallet_create_sign_name)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pwd)||pwd.length<6) {
                SmartToast.showInCenter(R.string.wallet_pwd_six)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(repeatPwd) || repeatPwd.length < 6) {
                SmartToast.showInCenter(R.string.wallet_pwd_six)
                return@setOnClickListener
            }
            if (pwd != repeatPwd) {
                SmartToast.showInCenter(R.string.wallet_create_sign_pwd_not)
                return@setOnClickListener
            }
            mViewModel.walletModel.value?.let { model ->
                model.password = pwd
                model.name = walletName
            }
            Navigation.findNavController(it).navigate(R.id.action_navigation_create_to_navigation_zjc)
        }
        wallet_select_import_view.clickWithTrigger {
            Navigation.findNavController(it).navigate(R.id.action_navigation_create_wallet_to_leadInAccountFragment)
        }
    }

    override fun initData() {
    }
}