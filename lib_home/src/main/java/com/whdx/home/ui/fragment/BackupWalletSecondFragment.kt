package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
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
        mViewModel.insertSuccess.observe(viewLifecycleOwner, Observer {
            if (it){
                Navigation.findNavController(btnLeadToLogin).navigate(R.id.action_backupWalletSecondFragment_to_prohibitionStatementFragment)
            }
        })
    }

    override fun setLayoutResId()= R.layout.fragment_backup_wallet_second

    override fun initView() {
        mDataBinding.vm = mViewModel
        btnLeadToLogin.setOnClickListener {
            mViewModel.insertWallet()
        }
    }

    override fun initData() {
    }
}