package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentBackupWalletFirstBinding
import com.whdx.home.databinding.FragmentBackupWalletSecondBinding
import com.whdx.home.databinding.FragmentCheckZjcBinding
import com.whdx.home.databinding.FragmentWalletCreateBinding
import com.whdx.home.ui.activity.ExportKeyActivity
import com.whdx.home.ui.activity.MainActivity
import com.whdx.home.ui.dialog.InputPasswordDialog
import com.whdx.home.ui.dialog.WithdrawDialog
import com.whdx.home.util.ConfigHolder
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_backup_wallet_second.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BackupWalletSecondFragment:BaseBindingFragment<WalletViewModel,FragmentBackupWalletSecondBinding>() {
    override fun initVM(): WalletViewModel= requireActivity().getViewModel()

    override fun startObserve() {
        mViewModel.insertSuccess.observe(viewLifecycleOwner, Observer {
            if (it){
//                Navigation.findNavController(btnLeadToLogin).navigate(R.id.action_backupWalletSecondFragment_to_prohibitionStatementFragment)
                MainActivity.start(requireContext())
                requireActivity().finish()
            }
        })
    }

    override fun setLayoutResId()= R.layout.fragment_backup_wallet_second

    override fun initView() {
        mDataBinding.vm = mViewModel
        titleBar.setOnLeftClickListener { Navigation.findNavController(titleBar).navigateUp() }
        btnLeadToLogin.setOnClickListener {
            val withdrawDialog = WithdrawDialog(requireContext())
            withdrawDialog.setOnTextListener {
                if (it != mViewModel.walletModel.value?.password) {
                    SmartToast.error(getString(R.string.passwrod_error))
                    return@setOnTextListener
                }
                withdrawDialog.dismiss()
                mViewModel.insertWallet()
            }
            withdrawDialog.show()
        }
    }

    override fun initData() {
    }
}