package com.whdx.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.coder.zzq.smartshow.toast.SmartToast
import timber.log.Timber


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:29
 */
abstract class BaseFragment : Fragment() {
    lateinit var mRootView:View
//    lateinit var loadService:LoadService<*>
    private lateinit var progressDialogFragment: ProgressDialogFragment
    private var isLoaded = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(setLayoutResId(), container, false)
//        loadService= LoadSir.getDefault().register(mRootView){ reLoad()}
//        return loadService.loadLayout
        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("dhdhdh").e("Fragment onCreate")
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            onFragmentFirstVisible()
            isLoaded = true
        }
    }

    protected open fun onFragmentFirstVisible() {
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.tag("dhdhdh").e("Fragment onDestroyView")
        isLoaded = false
    }

    abstract fun setLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()

    open fun reLoad() {
        SmartToast.complete("wanc")
    }


    /**
     * 显示加载(转圈)对话框
     */
    fun showProgressDialog(message: String?) {
        if (!this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        if (!progressDialogFragment.isAdded) {
            activity?.supportFragmentManager?.let { progressDialogFragment.show(it, message, false) }
        }
    }

    /**
     * 隐藏加载(转圈)对话框
     */
    fun dismissProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismissAllowingStateLoss()
        }
    }

}