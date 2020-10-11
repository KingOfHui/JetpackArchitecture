package com.whdx.base.util.binding

import android.app.Activity
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.whdx.base.ui.view.JRecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.whdx.base.util.binding.recyclerview.LineDivideManagers

@BindingAdapter("linearManager")
fun setLineManager(
    recyclerView: RecyclerView,
    lineManagerFactory: LineDivideManagers.LineManagerFactory
) {
    recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView))
}

@BindingAdapter("itemAnimator")
fun setItemAnimator(recyclerView: RecyclerView, itemAnimator: RecyclerView.ItemAnimator) {
    recyclerView.itemAnimator = itemAnimator
}

@BindingAdapter(value = ["refreshing", "moreLoading", "hasMore"], requireAll = false)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    refreshing: Boolean,
    moreLoading: Boolean,
    hasMore: Boolean?

) {
    if (!refreshing) {
        smartLayout.finishRefresh()
        smartLayout.finishLoadMore()
    }
    if (!moreLoading) {
    }

    if (hasMore != null)
        smartLayout.setNoMoreData(!hasMore)//调用次方法会停止刷新动作
}

@BindingAdapter(value = ["refreshEnable"])
fun bindSmartRefreshLayoutRefresh(
    smartLayout: SmartRefreshLayout,
    refreshEnable: Boolean
) {
    smartLayout.setEnableRefresh(refreshEnable)
}

@BindingAdapter(
    value = ["autoRefresh"]
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    autoRefresh: Boolean
) {
    if (autoRefresh) smartLayout.autoRefresh()
}

@BindingAdapter(
    value = ["onRefreshListener", "onLoadMoreListener"],
    requireAll = false
)
fun bindListener(
    smartLayout: SmartRefreshLayout,
    refreshListener: OnRefreshListener?,
    loadMoreListener: OnLoadMoreListener?
) {
    smartLayout.setOnRefreshListener(refreshListener)
    smartLayout.setOnLoadMoreListener(loadMoreListener)
}


@BindingAdapter(value = ["gone"])
fun bindGone(v: View, gone: Boolean) {
    v.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["invisible"])
fun bindInvisible(v: View, invisible: Boolean) {
    v.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter(value = ["url", "radius"])
fun bindImage(iv: ImageView, url: String?, radius: Int?) {
    // TODO: 2020/9/25 0025
}

@BindingAdapter(value = ["imageId"])
fun bindImage(iv: ImageView, id: Int?) {
    if (id != null)
        iv.setImageResource(id)
}


@BindingAdapter(value = ["select"])
fun bindSelect(v: View, select: Boolean) {
    v.isSelected = select
}

@BindingAdapter(value = ["back"])
fun bindBackAction(v: View, select: Boolean) {
    if (select) {
        v.setOnClickListener {
            (v.context as Activity).onBackPressed()
        }
    }
}

@BindingAdapter(value = ["open"])
fun bindDrawer(v: DrawerLayout, open: Boolean) {
    if (open) {
        v.openDrawer(Gravity.LEFT)
    } else {
        v.closeDrawer(Gravity.LEFT)
    }
}


@BindingAdapter(value = ["html"])
fun bindHtml(tv: TextView, text: String) {
    tv.text = Html.fromHtml(text)
}


