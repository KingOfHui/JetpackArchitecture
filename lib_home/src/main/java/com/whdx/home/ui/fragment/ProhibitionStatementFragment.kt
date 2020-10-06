package com.whdx.home.ui.fragment

import com.whdx.base.ui.fragment.BaseFragment
import com.whdx.home.R
import com.whdx.home.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_prohibition_statement.*

class ProhibitionStatementFragment:BaseFragment() {
    override fun setLayoutResId()= R.layout.fragment_prohibition_statement

    override fun initView() {
        stop_sure_btn.setOnClickListener {
            MainActivity.start(requireContext())
            requireActivity().finish()
        }
    }

    override fun initData() {
    }
}