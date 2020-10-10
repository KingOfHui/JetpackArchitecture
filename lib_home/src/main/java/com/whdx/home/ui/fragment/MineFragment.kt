package com.whdx.home.ui.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.data.data.user.InviteListItem
import com.whdx.home.vm.HomeViewModel
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
            if (it.isNullOrEmpty()) {
                mAdapter.setEmptyView(R.layout.layout_empty)
            } else {
                mAdapter.setList(it)
            }
        })
        mViewModel.userInfoLive.observe(viewLifecycleOwner, Observer {
            tv_open_bid.text = if (it.referer_id.isNullOrEmpty()) {
                "未开通BID"
            } else {
                "已开通BID"
            }
        })
    }

    override fun setLayoutResId() = R.layout.fragment_mine;

    override fun initView() {
        textView.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    LoginActivity::class.java
                )
            )
        }
        iv_setting.clickWithTrigger { SettingActivity.start(requireContext()) }
        tv_open_bid.clickWithTrigger {
            showOpenDialog()
        }
        tv_withdraw.clickWithTrigger {
            mViewModel.userInfoLive.value?.let {
                if (it.referer_id.isNullOrEmpty()) {
                    showOpenDialog()
                } else{
                    InviteCodeDialog.show(requireContext(),it.invite_code)
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
                    it.executePendingBindings()
                }

            }

        }
        rvInvite.adapter = mAdapter
        mDataBinding.vm = mViewModel
    }

    private fun showOpenDialog() {
        val warningDialog = WarningDialog(requireContext())
        warningDialog.setOnClickListener {
            val inputPasswordDialog = InputPasswordDialog(requireContext(), "请输入邀请码")
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