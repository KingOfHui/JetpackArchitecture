package com.whdx.home.ui.fragment

import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentBackupWalletFirstBinding
import com.whdx.home.databinding.FragmentBackupWalletSecondBinding
import com.whdx.home.databinding.FragmentCheckZjcBinding
import com.whdx.home.databinding.FragmentWalletCreateBinding
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_backup_wallet_second.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BackupWalletSecondFragment:BaseBindingFragment<WalletViewModel,FragmentBackupWalletSecondBinding>() {
    override fun initVM(): WalletViewModel= requireActivity().getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_backup_wallet_second

    override fun initView() {
        btnLeadToLogin.setOnClickListener {

            mViewModel.insertWallet()

        }
    }

    override fun initData() {
    }
}