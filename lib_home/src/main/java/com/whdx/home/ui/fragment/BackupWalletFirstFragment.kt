package com.whdx.home.ui.fragment

import androidx.navigation.Navigation
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentBackupWalletFirstBinding
import com.whdx.home.databinding.FragmentCheckZjcBinding
import com.whdx.home.databinding.FragmentWalletCreateBinding
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_backup_wallet_first.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BackupWalletFirstFragment:BaseBindingFragment<WalletViewModel,FragmentBackupWalletFirstBinding>() {
    override fun initVM(): WalletViewModel= getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_backup_wallet_first

    override fun initView() {
        next_view_btn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_backupWalletFirstFragment_to_backupWalletSecondFragment)
        }
    }

    override fun initData() {
    }
}