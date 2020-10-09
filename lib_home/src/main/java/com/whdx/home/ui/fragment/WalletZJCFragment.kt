package com.whdx.home.ui.fragment

import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.home.R
import com.whdx.home.databinding.FragmentWalletZjcBinding
import com.whdx.home.util.BtcWalletUtils
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_wallet_zjc.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WalletZJCFragment:BaseBindingFragment<WalletViewModel,FragmentWalletZjcBinding>() {
    override fun initVM(): WalletViewModel= requireActivity().getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_wallet_zjc

    override fun initView() {
        titleBar.setOnLeftClickListener { Navigation.findNavController(titleBar).navigateUp() }

        val btcDo = BtcWalletUtils.create()
        val listZjc = btcDo.listZjc
        qfl_content.removeAllViews()

        for (i in listZjc.indices) {
            val tempWord: String = listZjc[i]
            val view =
                View.inflate(requireContext(), R.layout.view_zjc_text_view, null)
            val tv = view.findViewById<TextView>(R.id.walletCreateWords)
            tv.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_rect_black, null)
            tv.text = tempWord
            qfl_content.addView(view)
        }
        mViewModel.apply {
            mBtcDo.value = btcDo
            mMnemonic.value = listZjc
        }
        btnNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_zjc_to_checkZJCFragment)
        }

    }

    override fun initData() {
    }
}