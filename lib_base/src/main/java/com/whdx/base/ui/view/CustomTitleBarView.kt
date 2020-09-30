package com.whdx.base.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.whdx.base.R
import com.whdx.base.util.ext.clickWithTrigger
import com.whdx.base.util.ext.color
import com.whdx.base.util.ext.text
import kotlinx.android.synthetic.main.layout_custom_title_bar_view.view.*
import org.jetbrains.anko.backgroundColor

/**
 * @Description 通用titleBar
 * @Author dinghui
 * @Date 2020/9/30 0030 9:16
 */
class CustomTitleBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {
    private var mLeftImg: Int = 0
    private var mLeftTextStr: String? = null
    private var mLeftTextId: Int = 0
    private var mRightTextStr: String? = null
    private var mRightTextId: Int = 0
    private var mRightImg1: Int = 0
    private var mRightImg2: Int = 0
    private var mRightImg3: Int = 0
    private var mShowLeftImg: Boolean = false
    private var mShowRightImg1: Boolean = false
    private var mShowRightImg2: Boolean = false
    private var mShowRightImg3: Boolean = false
    private var mTitleTextStr: String? = null
    private var mTitleTextId: Int = 0
    private var mTextColor: Int = 0
    private var mRightTextColor: Int = 0
    private var mNavigationBar: Int = 0
    private var mShowTabLayout: Boolean = false

    init {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBarView)
        mLeftTextId =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_left_textId, 0)
        mLeftTextStr =
            obtainStyledAttributes.getString(R.styleable.CustomTitleBarView_left_textStr)
        mRightTextId =
            obtainStyledAttributes.getResourceId(
                R.styleable.CustomTitleBarView_right_textId,
                0
            )
        mRightTextStr =
            obtainStyledAttributes.getString(R.styleable.CustomTitleBarView_right_textStr)
        mRightTextColor = obtainStyledAttributes.getColor(R.styleable.CustomTitleBarView_right_text_color, 0)

        mLeftImg =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_left_img, 0)
        mShowLeftImg = obtainStyledAttributes.getBoolean(
            R.styleable.CustomTitleBarView_isShow_left_img,
            false
        )
        mRightImg1 =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_right_img1, 0)
        mRightImg2 =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_right_img2, 0)
        mRightImg3 =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_right_img3, 0)
        mShowRightImg1 = obtainStyledAttributes.getBoolean(
            R.styleable.CustomTitleBarView_isShow_right_img1,
            false
        )
        mShowRightImg2 = obtainStyledAttributes.getBoolean(
            R.styleable.CustomTitleBarView_isShow_right_img2,
            false
        )
        mRightImg3 =
            obtainStyledAttributes.getResourceId(R.styleable.CustomTitleBarView_right_img3, 0)
        mShowRightImg3 = obtainStyledAttributes.getBoolean(
            R.styleable.CustomTitleBarView_isShow_right_img2,
            false
        )
        mTitleTextId = obtainStyledAttributes.getResourceId(
            R.styleable.CustomTitleBarView_title_textId,
            0
        )
        mTitleTextStr =
            obtainStyledAttributes.getString(R.styleable.CustomTitleBarView_title_textStr)
        mTextColor =
            obtainStyledAttributes.getColor(R.styleable.CustomTitleBarView_text_color, 0)
        mShowTabLayout = obtainStyledAttributes.getBoolean(
            R.styleable.CustomTitleBarView_isShow_tab_layout,
            false
        )
        mNavigationBar =
            obtainStyledAttributes.getColor(R.styleable.CustomTitleBarView_bg_color, 0)
        initView()
        setContentInsetsRelative(0, 0)
        obtainStyledAttributes.recycle()
    }


    private fun initView() {
        View.inflate(context, R.layout.layout_custom_title_bar_view, this)
        mLeftTextStr?.let {
            tvLeftTitleNavigationBar.text = mLeftTextStr
            tvLeftTitleNavigationBar.visibility = View.VISIBLE
        }
        if (mLeftTextId != 0) {
            tvLeftTitleNavigationBar.setText(mLeftTextId)
            tvLeftTitleNavigationBar.visibility = View.VISIBLE
        }
        mRightTextStr?.let {
            tvRightTitleNavigationBar.text = mRightTextStr
            tvRightTitleNavigationBar.visibility = View.VISIBLE
        }
        if (mRightTextId != 0) {
            tvRightTitleNavigationBar.setText(mRightTextId)
            tvRightTitleNavigationBar.visibility = View.VISIBLE
        }
        if (mLeftImg != 0) {
            ivBackNavigationBar.setImageResource(mLeftImg)
        }
        ivBackNavigationBar.visibility = if (mShowLeftImg) View.VISIBLE else View.GONE

        if (mRightImg1 != 0) {
            ivRightNavigationBarOne.setImageResource(mRightImg1)
        }
        ivRightNavigationBarOne.visibility = if (mShowRightImg1) View.VISIBLE else View.GONE
        if (mRightImg2 != 0) {
            ivRightNavigationBarTwo.setImageResource(mRightImg2)
        }
        ivRightNavigationBarTwo.visibility = if (mShowRightImg2) View.VISIBLE else View.GONE
        mTitleTextStr?.let {
            tvTitleNavigationBar.text = mTitleTextStr
            tvTitleNavigationBar.visibility = View.VISIBLE
        }
        if (mTitleTextId != 0) {
            tvTitleNavigationBar.setText(mTitleTextId)
            tvTitleNavigationBar.visibility = View.VISIBLE
        }
        if (mTextColor != 0) {
            tvTitleNavigationBar.setTextColor(mTextColor)
        }
        if (mRightTextColor != 0) {
            tvRightTitleNavigationBar.setTextColor(mRightTextColor)
        }
        tlNavigationBar.visibility = if (mShowTabLayout) View.VISIBLE else View.GONE

        if (mNavigationBar != 0) {
            navigationBarBg.backgroundColor = mNavigationBar
        }
        ivRightNavigationBarTwo.clickWithTrigger {
//            context.startActivity<SettingActivity>()
        }
    }
    fun setNavigationBarBackgroundColor(colorRes: Int) {
        navigationBarBg.backgroundColor = color(colorRes)
    }

    fun setTitleNavigationBarColor(colorRes: Int) {
        tvTitleNavigationBar.setTextColor(color(colorRes))
    }

    fun setLeftTitleNavigationBarText(textRes: Int) {
        tvLeftTitleNavigationBar.visibility = View.VISIBLE
        tvLeftTitleNavigationBar.text = text(textRes)
    }

    fun setLeftTitleNavigationBarText(textRes: String) {
        tvLeftTitleNavigationBar.visibility = View.VISIBLE
        tvLeftTitleNavigationBar.text = textRes
    }

    fun setIvRightNavigationBarOne(drawableRes: Int) {
        ivRightNavigationBarOne.visibility = View.VISIBLE
        ivRightNavigationBarOne.setImageResource(drawableRes)
    }


    fun setTabLayoutData(tablayoutTitle: Array<String?>, viewPager2: ViewPager2) {
        //viewpage2 tablayout 联动
        TabLayoutMediator(tlNavigationBar, viewPager2) { tab, position ->
            tab.text = tablayoutTitle[position]

        }.attach()
        tlNavigationBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }
        })

    }
}