package com.whdx.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.whdx.base.R
import kotlinx.android.synthetic.main.fragment_progress_dialog.*

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0021 19:00
 */
class ProgressDialogFragment : DialogFragment() {

    private var message: String? = null

    companion object {
        fun newInstance() =
            ProgressDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessage.text = message ?: getString(R.string.loading)
    }

    fun show(
        fragmentManager: FragmentManager,
        message: String?,
        isCancelable: Boolean = false
    ) {
        this.message = message
        this.isCancelable = isCancelable
        try {
            show(fragmentManager, "progressDialogFragment")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}