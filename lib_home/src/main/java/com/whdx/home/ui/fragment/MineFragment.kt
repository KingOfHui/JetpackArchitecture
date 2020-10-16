package com.whdx.home.ui.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.data.data.user.InviteListItem
import com.whdx.home.ui.activity.LoginActivity
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMineBinding
import com.whdx.home.databinding.ItemMyInviteBinding
import com.whdx.home.ui.activity.SettingActivity
import com.whdx.home.ui.dialog.InputPasswordDialog
import com.whdx.home.ui.dialog.InviteCodeDialog
import com.whdx.home.ui.dialog.WarningDialog
import com.whdx.home.vm.MineViewModel
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/24 0024 9:56
 */
class MineFragment : BaseBindingFragment<MineViewModel, FragmentMineBinding>() {

    lateinit var mAdapter: BaseQuickAdapter<InviteListItem, BaseDataBindingHolder<ItemMyInviteBinding>>

    override fun initVM(): MineViewModel = getViewModel()

    override fun startObserve() {

        mViewModel.inviteListLive.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
        })
        mViewModel.userInfoLive.observe(viewLifecycleOwner, Observer {
            tv_open_bid.text = if (it.referer_id.isNullOrEmpty()) {
                getString(R.string.no_open_bid)
            } else {
                getString(R.string.already_open_bid)
            }
        })
        mViewModel.openSuccess.observe(viewLifecycleOwner, Observer {
            inputPasswordDialog.dismiss()
        })
    }

    override fun setLayoutResId() = R.layout.fragment_mine;

    override fun initView() {
        iv_setting.clickWithTrigger { SettingActivity.start(requireContext()) }
        tv_open_bid.clickWithTrigger {
            mViewModel.userInfoLive.value?.let {
                if (it.referer_id.isNullOrEmpty()) {
                    showOpenDialog()
                }
            }
        }
        tv_withdraw.clickWithTrigger {
            mViewModel.userInfoLive.value?.let {
                if (it.referer_id.isNullOrEmpty()) {
                    showOpenDialog()
                } else {
                    InviteCodeDialog.show(requireContext(), it.invite_code)
                }
            }
        }
        mAdapter = object :
            BaseQuickAdapter<InviteListItem, BaseDataBindingHolder<ItemMyInviteBinding>>(R.layout.item_my_invite) {
            override fun convert(
                holder: BaseDataBindingHolder<ItemMyInviteBinding>,
                item: InviteListItem
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.tvDegree.text = when (item.vip_degree) {
                        /**
                         * "satellit
                        "star">恒星
                        "planet">
                        "Galaxy">
                        "universe
                         */
                        1 -> getString(R.string.satellite)
                        2 -> getString(R.string.star)
                        3 -> getString(R.string.planet)
                        4 -> getString(R.string.Galaxy)
                        5 -> getString(R.string.universe)
                        else -> getString(R.string.none)
                    }
                    it.executePendingBindings()
                }

            }

        }
        rvInvite.adapter = mAdapter
        mDataBinding.vm = mViewModel
    }

    private lateinit var inputPasswordDialog: InputPasswordDialog;
    private fun showOpenDialog() {
        val warningDialog = WarningDialog(requireContext())
        warningDialog.setOnClickListener {
            inputPasswordDialog =
                InputPasswordDialog(requireContext(), getString(R.string.input_invite_code),true)
            inputPasswordDialog.setInputListener { code ->
                mViewModel.openBid(code)
            }
            inputPasswordDialog.show()
        }
        warningDialog.show()
    }

    override fun initData() {
        Timber.tag("dhdhdh").e("initData ThirdFragment")
        mViewModel.requestInviteData()
    }
}