package com.whdx.base.util.binding.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.whdx.base.ui.view.JRecyclerView
import com.whdx.base.R

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/25 0025 11:05
 */
class DividerLine(val context: Context, mode: LineDrawMode? = null, dividerSize: Int = 1) :
    RecyclerView.ItemDecoration() {


    private val TAG = DividerLine::class.java.canonicalName

    //默认分隔线厚度为2dp
    private val DEFAULT_DIVIDER_SIZE = 1

    //控制分隔线的属性,值为一个drawable
    private val ATTRS = intArrayOf(android.R.attr.listDivider)

    private val drawables = intArrayOf(R.drawable.shape_line_divider_gray,R.drawable.shape_line_divider_transport);
    private val drawableSizes = intArrayOf(1,15)


    //divider对应的drawable
    private var dividerDrawable: Drawable? = null
    private var mContext: Context? = null
    private var dividerSize = 0

    //默认为null
    private var mMode: LineDrawMode? = null
    init {
        mContext = context
        //获取样式中对应的属性值
        val attrArray = context.obtainStyledAttributes(ATTRS)
        attrArray.recycle()
        dividerDrawable = ColorDrawable(Color.parseColor("#4C587D"))
//        dividerDrawable = ContextCompat.getDrawable(context, drawables[0])
        mMode = mode
        this.dividerSize = dividerSize
    }
    /**
     * 分隔线绘制模式,水平，垂直，两者都绘制
     */
    enum class LineDrawMode {
        HORIZONTAL, VERTICAL, BOTH
    }

    fun getDividerSize(): Int {
        return dividerSize
    }

    fun setDividerSize(dividerSize: Int) {
        this.dividerSize = dividerSize
    }

    fun getMode(): LineDrawMode? {
        return mMode
    }

    fun setMode(mode: LineDrawMode?) {
        mMode = mode
    }

    /**
     * Item绘制完毕之后绘制分隔线
     * 根据不同的模式绘制不同的分隔线
     *
     * @param c
     * @param parent
     * @param state
     */
    override fun onDrawOver(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDrawOver(c, parent, state)
        checkNotNull(getMode()) { "assign LineDrawMode,please!" }
        when (getMode()) {
            LineDrawMode.VERTICAL -> drawVertical(c, parent, state)
            LineDrawMode.HORIZONTAL -> drawHorizontal(c, parent, state)
            LineDrawMode.BOTH -> {
                drawHorizontal(c, parent, state)
                drawVertical(c, parent, state)
            }
        }
    }

    /**
     * 绘制垂直分隔线
     *
     * @param c
     * @param parent
     * @param state
     */
    private fun drawVertical(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                .layoutParams as RecyclerView.LayoutParams
            val top = child.top - params.topMargin
            val bottom = child.bottom + params.bottomMargin
            val left = child.right + params.rightMargin
            val right = if (getDividerSize() == 0) left + dip2px(
                mContext,
                DEFAULT_DIVIDER_SIZE.toFloat()
            ) else left + getDividerSize()
            dividerDrawable!!.setBounds(left, top, right, bottom)
            dividerDrawable!!.draw(c)
        }
    }

    /**
     * 绘制水平分隔线
     *
     * @param c
     * @param parent
     * @param state
     */
    private fun drawHorizontal(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            //分别为每个item绘制分隔线,首先要计算出item的边缘在哪里,给分隔线定位,定界
            val child = parent.getChildAt(i)
            //RecyclerView的LayoutManager继承自ViewGroup,支持了margin
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            //child的左边缘(也是分隔线的左边)
            val left = child.left - params.leftMargin
            //child的底边缘(恰好是分隔线的顶边)
            val top = child.bottom + params.topMargin
            //child的右边(也是分隔线的右边)
            val right = child.right - params.rightMargin
            //分隔线的底边所在的位置(那就是分隔线的顶边加上分隔线的高度)
            val bottom = if (getDividerSize() == 0) top + dip2px(
                mContext,
                DEFAULT_DIVIDER_SIZE.toFloat()
            ) else top + getDividerSize()
            dividerDrawable!!.setBounds(left, top, right, bottom)
            //画上去
            dividerDrawable!!.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect!!, view!!, parent!!, state!!)
//        outRect.bottom = getDividerSize() == 0 ? dip2px(mContext, DEFAULT_DIVIDER_SIZE) : getDividerSize();
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context（DisplayMetrics类中属性density）
     * @return
     */
    fun dip2px(context: Context?, dipValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }
}