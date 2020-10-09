package com.whdx.home.ui.fragment

import android.text.TextUtils
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.home.R
import com.whdx.home.util.BtcWalletUtils
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_lead_private_key.*
import kotlinx.android.synthetic.main.fragment_zjc_lead.import_zjc_content
import kotlinx.android.synthetic.main.fragment_zjc_lead.input_account_pwd
import kotlinx.android.synthetic.main.fragment_zjc_lead.input_account_pwd_two
import kotlinx.android.synthetic.main.fragment_zjc_lead.input_submit_view
import kotlinx.android.synthetic.main.fragment_zjc_lead.lead_in_create_view
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @Description
 * @Author dinghui
 * @Date 2020/10/9 0009 10:39
 */
class LeadPrivateKeyFragment:BaseBindingFragment<WalletViewModel,ViewDataBinding>() {
    override fun initVM(): WalletViewModel = requireActivity().getViewModel()

    override fun startObserve() {
        mViewModel.insertSuccess.observe(viewLifecycleOwner, Observer {
            if (it){
                Navigation.findNavController(input_submit_view).navigate(R.id.action_backupWalletSecondFragment_to_prohibitionStatementFragment)
            }
        })
    }

    override fun setLayoutResId()= R.layout.fragment_lead_private_key

    override fun initView() {
        lead_in_create_view.clickWithTrigger {
            Navigation.findNavController(it).navigate(R.id.action_leadInAccountFragment_to_navigation_create_wallet)
        }
        input_submit_view.clickWithTrigger {

            val trim = import_zjc_content.text.toString().trim()
            if (trim.isEmpty()) {
                SmartToast.show("请输入您的私钥")
                return@clickWithTrigger
            }
            if (!checkbox.isChecked) {
                SmartToast.showInCenter(getString(R.string.wallet_create_xieyi))
                return@clickWithTrigger
            }
            val pwd = input_account_pwd.text.toString().trim()
            val repeatPwd = input_account_pwd_two.text.toString().trim()
            if (TextUtils.isEmpty(pwd)||pwd.length<6) {
                SmartToast.showInCenter(R.string.wallet_pwd_six)
                return@clickWithTrigger
            }
            if (TextUtils.isEmpty(repeatPwd) || repeatPwd.length < 6) {
                SmartToast.showInCenter(R.string.wallet_pwd_six)
                return@clickWithTrigger
            }
            if (pwd != repeatPwd) {
                SmartToast.showInCenter(R.string.wallet_create_sign_pwd_not)
                return@clickWithTrigger
            }
            val btcDo = BtcWalletUtils.findWalletByKey(trim)
            if (btcDo == null) {
                SmartToast.showInCenter(getString(R.string.wrong_zjc_wrong))
                return@clickWithTrigger
            }
            mViewModel.apply {
                mBtcDo.value = btcDo
                walletModel.value?.let { model ->
                    model.password = pwd
                    model.name = "wallet"
                    mViewModel.insertWallet()
                }
            }
        }
    }

    override fun initData() {
    }
}