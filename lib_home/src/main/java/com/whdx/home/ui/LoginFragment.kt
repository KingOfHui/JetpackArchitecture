package com.whdx.home.ui

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.user.User
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 11:50
 */
class LoginFragment :BaseBindingFragment<HomeViewModel,FragmentLoginBinding>() {
    override fun initVM(): HomeViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mUser.observe(this,Observer<User>{
            mDataBinding.user = it
        })
    }

    override fun setLayoutResId()= R.layout.fragment_login;

    override fun initView() {
        login.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_create_to_navigation_zjc);
            val user = User()
            user.username = "jflskd"
            mViewModel.mUser.value = user
        }
        toForget.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_login_to_navigation_forget)
        }
    }

    override fun initData() {
        mDataBinding.vm = mViewModel
        Timber.tag("dhdhdh").e("initData LoginFragment")
    }
}