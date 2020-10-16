package com.whdx.home.ui.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.jeremyliao.liveeventbus.LiveEventBus
import com.whdx.base.ui.fragment.BaseBindingFragment
import com.whdx.base.util.ext.REFRESH_BALANCE
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.data.data.product.InvestProductItem
import com.whdx.home.R
import com.whdx.home.databinding.FragmentMyCloudBinding
import com.whdx.home.databinding.ItemMyCloudMineralBinding
import com.whdx.home.databinding.LayoutHeaderMyCloudBinding
import com.whdx.home.ui.activity.CloudBonusActivity
import com.whdx.home.vm.MyCloudViewModel
import kotlinx.android.synthetic.main.activity_cloud_bonus.*
import kotlinx.android.synthetic.main.fragment_my_cloud.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.math.BigDecimal

class MyCloudComputeFragment : BaseBindingFragment<MyCloudViewModel,FragmentMyCloudBinding>() {
    lateinit var adapter: BaseQuickAdapter<InvestProductItem, BaseDataBindingHolder<ItemMyCloudMineralBinding>>;
    override fun initVM(): MyCloudViewModel = getViewModel()

    override fun startObserve() {
        mViewModel.mInvestList.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                adapter.setEmptyView(R.layout.layout_empty)
            } else {
                adapter.setList(it)
            }
        })
        LiveEventBus.get(REFRESH_BALANCE).observe(viewLifecycleOwner, Observer {
            if ((it as Boolean)) {
                mViewModel.getMyStorage()
            }
        })
    }

    override fun setLayoutResId() = R.layout.fragment_my_cloud;

    override fun initView() {
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = object :  BaseQuickAdapter<InvestProductItem, BaseDataBindingHolder<ItemMyCloudMineralBinding>>(R.layout.item_my_cloud_mineral) {

            override fun convert(holder: BaseDataBindingHolder<ItemMyCloudMineralBinding>, item: InvestProductItem) {
                holder.dataBinding?.let {
                    it.model = item
//                    it.tvStorage.text = item.format(getString(R.string.prod_storage),item.prod_storage)
//                    it.tvPerPrice.text = item.format(getString(R.string.price_usdt),item.amount.stripTrailingZeros().toPlainString())
//                    it.tvBuyNum.text = item.format(getString(R.string.buy_num),item.quantity.toString())
                    it.executePendingBindings()
                    it.tvStorage.text = if (item.prod_storage >= BigDecimal.valueOf(1000)) {
                        String.format(getString(R.string.power_is),item.prod_storage.divide(
                            BigDecimal.valueOf(1000)).stripTrailingZeros().toPlainString()+"P")
                    } else{
                        String.format(getString(R.string.power_is),item.prod_storage.stripTrailingZeros().toPlainString()+"T")
                    }
                }
                holder.itemView.clickWithTrigger {
                    CloudBonusActivity.start(requireContext(),item)
                }
            }

        }
        rv.adapter = adapter
//        val binding:LayoutHeaderMyCloudBinding  = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.layout_header_my_cloud, null, false)
//        adapter.addHeaderView(binding.root)
//        binding.vm = mViewModel
//        binding.lifecycleOwner = this
//        binding.executePendingBindings()
        mDataBinding.vm = mViewModel
    }

    override fun initData() {
        mViewModel.getMyStorage()
    }
}