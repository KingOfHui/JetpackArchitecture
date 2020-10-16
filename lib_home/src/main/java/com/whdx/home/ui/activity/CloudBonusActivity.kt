package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.data.data.product.InvestProductItem
import com.whdx.data.data.product.ProductItem
import com.whdx.data.data.wallet.InvestBonusItem
import com.whdx.home.R
import com.whdx.home.databinding.ActivityCloudBonusBinding
import com.whdx.home.databinding.ItemCloudMineralBinding
import com.whdx.home.databinding.ItemInvestBonusBinding
import com.whdx.home.vm.BonusViewModel
import kotlinx.android.synthetic.main.activity_cloud_bonus.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.math.BigDecimal

class CloudBonusActivity:BaseBindingActivity<BonusViewModel,ActivityCloudBonusBinding>() {
    lateinit var adapter:BaseQuickAdapter<InvestBonusItem,BaseDataBindingHolder<ItemInvestBonusBinding>>

    override fun initVM(): BonusViewModel= getViewModel()

    override fun startObserve() {
        mViewModel.mInvestBonusItemLive.observe(this, Observer {
//            if (it.isNullOrEmpty()) {
//                adapter.setEmptyView(R.layout.layout_empty)
//            } else {
                adapter.setList(it)
//            }
        })
    }

    override fun setLayoutId()= R.layout.activity_cloud_bonus

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.vm = mViewModel
        titleBar.setOnLeftClickListener { finish() }
        adapter= object :BaseQuickAdapter<InvestBonusItem,BaseDataBindingHolder<ItemInvestBonusBinding>>(R.layout.item_invest_bonus){
            override fun convert(
                holder: BaseDataBindingHolder<ItemInvestBonusBinding>,
                item: InvestBonusItem
            ) {
                holder.dataBinding?.let {
                    it.model = item
                    it.executePendingBindings()
                }
            }

        }
        rvBonus.adapter = adapter
    }

    override fun initData() {
        val productItem = intent.extras?.getSerializable("investBonusItem") as InvestProductItem
        mViewModel.refresh(productItem.id)
        mDataBinding.model= productItem
        tvStorage.text = if (productItem.prod_storage >= BigDecimal.valueOf(1000)) {
            String.format(getString(R.string.power_is),productItem.prod_storage.divide(BigDecimal.valueOf(1000)).stripTrailingZeros().toPlainString()+"P")
        } else{
            String.format(getString(R.string.power_is),productItem.prod_storage.stripTrailingZeros().toPlainString()+"T")
        }
    }

    companion object{
        fun start(context: Context, investBonusItem: InvestProductItem) {
            val intent = Intent(context, CloudBonusActivity::class.java).also {
                it.putExtra("investBonusItem", investBonusItem)
            }
            context.startActivity(intent)
        }
    }
}