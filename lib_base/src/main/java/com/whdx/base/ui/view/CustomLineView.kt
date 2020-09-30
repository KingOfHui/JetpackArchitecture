package com.whdx.base.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.whdx.base.R

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/30 0030 11:59
 */
class CustomLineView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.layout_custom_line_view, this)

    }
}