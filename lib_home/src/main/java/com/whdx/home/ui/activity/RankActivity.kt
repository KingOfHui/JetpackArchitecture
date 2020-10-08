package com.whdx.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.whdx.base.ui.activity.BaseBindingActivity
import com.whdx.base.ui.activity.BaseVMActivity
import com.whdx.base.ui.fragment.BaseVMFragment
import com.whdx.data.data.Rank
import com.whdx.home.R
import com.whdx.home.databinding.ActivityRankBinding
import com.whdx.home.databinding.ItemRankBinding
import com.whdx.home.vm.RankViewModel
import kotlinx.android.synthetic.main.activity_rank.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RankActivity : BaseBindingActivity<RankViewModel,ActivityRankBinding>() {

    lateinit var mAdapter: BaseQuickAdapter<Rank, BaseDataBindingHolder<ItemRankBinding>>
    override fun initVM(): RankViewModel = getViewModel()
    override fun startObserve() {
        mViewModel.mRankList.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                mAdapter.setEmptyView(R.layout.layout_empty)
            } else {
                mAdapter.setList(it)
            }
        })
    }

    override fun setLayoutId() = R.layout.activity_rank

    override fun initView(savedInstanceState: Bundle?) {
        titleBar.setOnLeftClickListener { finish() }
        mAdapter = object :
            BaseQuickAdapter<Rank, BaseDataBindingHolder<ItemRankBinding>>(R.layout.item_rank) {
            override fun convert(holder: BaseDataBindingHolder<ItemRankBinding>, item: Rank) {
                holder.dataBinding?.let {
                    it.tvUser.isInvisible = holder.adapterPosition >= 3
                    it.ivRank.isInvisible = holder.adapterPosition < 3
                    it.ivRank.background = when (holder.adapterPosition) {
                        0 -> ResourcesCompat.getDrawable(resources, R.mipmap.ic_rank_first, null)
                        1 -> ResourcesCompat.getDrawable(resources, R.mipmap.ic_rank_second, null)
                        else -> ResourcesCompat.getDrawable(resources, R.mipmap.ic_rank_three, null)
                    }
                    it.model = item
                    it.executePendingBindings()
                }
            }
        }
        rvRank.adapter = mAdapter
        mDataBinding.vm = mViewModel
    }

    override fun initData() {
        mViewModel.refresh()
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,RankActivity::class.java))
        }
    }
}