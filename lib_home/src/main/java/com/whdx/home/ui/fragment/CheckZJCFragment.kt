package com.whdx.home.ui.fragment

import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.CollectionUtil
import com.whdx.home.R
import com.whdx.home.databinding.FragmentCheckZjcBinding
import com.whdx.home.vm.WalletViewModel
import kotlinx.android.synthetic.main.fragment_check_zjc.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class CheckZJCFragment:BaseBindingFragment<WalletViewModel,FragmentCheckZjcBinding>() {
    val selectedWords:MutableList<String> = mutableListOf()
    override fun initVM(): WalletViewModel= requireActivity().getViewModel()

    override fun startObserve() {
    }

    override fun setLayoutResId()= R.layout.fragment_check_zjc

    override fun initView() {
        titleBar.setOnLeftClickListener { Navigation.findNavController(titleBar).navigateUp() }
        qfl_content.removeAllViews()
        val listZjc = mViewModel.mMnemonic.value?: listOf()

        val tempWordList = mutableListOf<String>()
        listZjc.forEach { tempWordList.add(it) }
        CollectionUtil.shuffle(tempWordList)
        for (i in tempWordList) {
            val tempWord: String = i
            val view =
                View.inflate(requireContext(), R.layout.view_zjc_text_view, null)
            val tv = view.findViewById<TextView>(R.id.walletCreateWords)
            tv.isSelected = true
            tv.setOnClickListener {
                selectedWords.add(tempWord)
                showSelectedWords()
            }
            tv.text = tempWord
            qfl_content.addView(view)
        }

        check_zjc_submit.setOnClickListener {
            if (selectedWords.isEmpty()) {
                SmartToast.showInCenter(R.string.zjc_check_fail)
                return@setOnClickListener
            }
            selectedWords.forEachIndexed { index, s ->
                if (listZjc[index]!=s) {
                    SmartToast.showInCenter(R.string.zjc_check_fail)
                    return@setOnClickListener
                }
            }
            Navigation.findNavController(it).navigate(R.id.action_checkZJCFragment_to_backupWalletFirstFragment)
        }

    }

    private fun showSelectedWords() {
        qfl_selected_words.removeAllViews()
        for (i in selectedWords) {
            val tempWord: String = i
            val view =
                View.inflate(requireContext(), R.layout.view_zjc_text_view, null)
            val tv = view.findViewById<TextView>(R.id.walletCreateWords)
            tv.background =
                ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_rect_black, null)
            tv.text = tempWord
            tv.setOnClickListener {
                selectedWords.remove(tempWord)
                showSelectedWords()
            }
            qfl_selected_words.addView(view)
        }
    }

    override fun initData() {
    }
}
