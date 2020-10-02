package com.whdx.base.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.whdx.base.R
import kotlinx.android.synthetic.main.layout_custom_vertical_view.view.*


class CustomVerticalView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomVerticalView)
        val topText = obtainStyledAttributes.getString(R.styleable.CustomVerticalView_top_textStr)
        val bottomText = obtainStyledAttributes.getString(R.styleable.CustomVerticalView_bottom_textStr)
        obtainStyledAttributes.recycle()
        val view = View.inflate(context, R.layout.layout_custom_vertical_view, this)
        tv_top.text = topText?:""
        tv_bottom.text = bottomText?:""

    }

    private var heigh = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (heigh == 0) {
            for (i in 0 until childCount) {
                val childView = getChildAt(i)
                measureChild(childView, widthMeasureSpec, heightMeasureSpec)
                heigh += childView.measuredHeight
            }
        }
        setMeasuredDimension(widthMeasureSpec, heigh)
    }

/*

    //控件默认的宽高
    var defaultWidth = 300
    var defaultHeight = 200

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val withSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT
            && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT
        ) {
            setMeasuredDimension(defaultWidth, defaultHeight)
        } else if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidth, heightSize)
        } else if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(withSize, defaultHeight)
        }
    }
*/

    /*override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    private fun measureHeight(heightMeasureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        val specSize = MeasureSpec.getSize(heightMeasureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = 70 as Int //高度默认大小
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = 100 //宽度默认大小
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }*/
}