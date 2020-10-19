package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.coder.zzq.smartshow.toast.SmartToast
import com.jeremyliao.liveeventbus.LiveEventBus
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.REFRESH_BALANCE
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.getLanguage
import com.whdx.data.data.product.ProductItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentSelectCloudBinding
import com.whdx.home.databinding.ItemCloudMineralBinding
import com.whdx.home.ui.dialog.InputPasswordDialog
import com.whdx.home.ui.dialog.LeaseDialog
import com.whdx.home.ui.dialog.WarningDialog
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.fragment_select_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.math.BigDecimal

class SelectCloudComputeFragment :
    BaseBindingFragment<SelectCloudViewModel, FragmentSelectCloudBinding>() {
    lateinit var adapter: BaseQuickAdapter<ProductItem, BaseDataBindingHolder<ItemCloudMineralBinding>>;
    var dialog: LeaseDialog?=null
    override fun initVM(): SelectCloudViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mProductItemList.observe(viewLifecycleOwner, Observer {
            if(mViewModel.isClearLive.value != false){
                adapter.data.clear()
            }
            adapter.addData(it)
        })
        mViewModel.mLeaseSuccessLive.observe(this, Observer {
            dialog?.dismiss()
        })
        mViewModel.openSuccess.observe(viewLifecycleOwner, Observer {
            inputPasswordDialog?.dismiss()
        })
        LiveEventBus.get(REFRESH_BALANCE).observe(this, Observer { mViewModel.getUSDTBalance() })
    }

    override fun setLayoutResId() = R.layout.fragment_select_cloud;

    override fun initView() {
        adapter = object :
            BaseQuickAdapter<ProductItem, BaseDataBindingHolder<ItemCloudMineralBinding>>(R.layout.item_cloud_mineral) {

            override fun convert(
                holder: BaseDataBindingHolder<ItemCloudMineralBinding>,
                item: ProductItem
            ) {
                holder.dataBinding?.let {
                    it.textView5.clickWithTrigger {
                        mViewModel.userInfoLive.value?.let {
                            if (it.referer_id.isNullOrEmpty()) {
                                showOpenDialog()
                            } else{
                                mViewModel.mBalanceLive.value?.let {
                                    dialog = LeaseDialog.show(
                                        requireContext(),
                                        it.balance,
                                        item.amount,
                                        item.id,
                                        mViewModel,
                                        viewLifecycleOwner
                                    )
                                }
                            }
                        }
                    }
                    it.tvReleaseMutiple.text = if (getLanguage() == 1) {
                        item.name
                    } else{
                        item.name_en?:""
                    }
                    it.tvStorage.text = if (item.storage >= BigDecimal.valueOf(1000)) {
                        String.format(getString(R.string.power_is),item.storage.divide(
                            BigDecimal.valueOf(1000)).stripTrailingZeros().toPlainString()+"P")
                    } else{
                        String.format(getString(R.string.power_is),item.storage.stripTrailingZeros().toPlainString()+"T")
                    }
                    it.model = item
                    it.executePendingBindings()
                }
            }

        }
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())
        mViewModel.refresh()
        mDataBinding.vm = mViewModel
    }

    private var inputPasswordDialog: InputPasswordDialog?=null
    private fun showOpenDialog() {
        val warningDialog = WarningDialog(requireContext())
        warningDialog.setOnClickListener {
            inputPasswordDialog =
                InputPasswordDialog(requireContext(), getString(R.string.input_invite_code),true)
            inputPasswordDialog?.setInputListener { code ->
                mViewModel.openBid(code)
            }
            inputPasswordDialog?.show()
        }
        warningDialog.show()
    }

    override fun initData() {
    }
}