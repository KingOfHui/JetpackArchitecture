package com.whdx.base.util.binding.recyclerview

import androidx.recyclerview.widget.RecyclerView

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/25 0025 11:09
 */
object LineDivideManagers {
    fun both(): LineManagerFactory {
        return object : LineManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.ItemDecoration {
                return DividerLine(recyclerView.context, DividerLine.LineDrawMode.BOTH)
            }
        }
    }

    fun horizontal(): LineManagerFactory {
        return object : LineManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.ItemDecoration {
                return DividerLine(recyclerView.context, DividerLine.LineDrawMode.HORIZONTAL)
            }
        }
    }

    fun vertical(): LineManagerFactory {
        return object : LineManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.ItemDecoration {
                return DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.VERTICAL)
            }
        }
    }

    interface LineManagerFactory {
        fun create(recyclerView: RecyclerView): RecyclerView.ItemDecoration
    }
}