package com.whdx.home.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.coder.zzq.smartshow.toast.SmartToast
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.getLanguage
import com.whdx.data.data.product.ProductItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentSelectCloudBinding
import com.whdx.home.databinding.ItemCloudMineralBinding
import com.whdx.home.ui.dialog.LeaseDialog
import com.whdx.home.vm.SelectCloudViewModel
import kotlinx.android.synthetic.main.fragment_select_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

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
                        mViewModel.mBalanceLive.value?.let {
                             dialog = LeaseDialog.show(
                                requireContext(),
                                it.balance,
                                item.amount,
                                item.id,
                                mViewModel,
                                viewLifecycleOwner
                            )
                        }?:let {
                            SmartToast.error("获取余额信息失败~")
                        }
                    }
                    it.tvReleaseMutiple.text = if (getLanguage() == 1) {
                        item.name
                    } else{
                        item.name_en?:""
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

    override fun initData() {
    }
}