package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.data.data.user.User
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.R
import com.whdx.home.databinding.FragmentNetDataBinding
import com.whdx.home.vm.NetDataViewModel
import com.whdx.provider.user.UserProviderWrap
import kotlinx.android.synthetic.main.fragment_net_data.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class NetDataFragment : BaseBindingFragment<NetDataViewModel, FragmentNetDataBinding>() {
    override fun initVM(): NetDataViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mNetDataLive.observe(viewLifecycleOwner, Observer {
            tvMyLevel.text = when (it.mine_level) {
                /**
                 * "satellit
                "star">恒星
                "planet">
                "Galaxy">
                "universe
                 */
                1 -> getString(R.string.satellite)
                2 -> getString(R.string.planet)
                3 -> getString(R.string.star)
                4 -> getString(R.string.Galaxy)
                5 -> getString(R.string.universe)
                else -> getString(R.string.none)
            }
//            距离下级还剩：212321有效业绩
            tvNextLevel.text = String.format("距离下级还剩：%s有效业绩",it.mine_next_performance?:"0")
        })
    }

    override fun setLayoutResId() = R.layout.fragment_net_data;

    override fun initView() {
        mDataBinding.vm = mViewModel
    }

    override fun initData() {
        mViewModel.getNetData()
        Timber.tag("dhdhdh").e("initData MineFragment")
    }

//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            mViewModel.getNetData()
//        }
//    }
}