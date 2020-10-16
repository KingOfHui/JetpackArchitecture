package com.whdx.base.util.ext

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.coder.zzq.smartshow.toast.SmartToast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.whdx.base.R
import com.whdx.base.util.TipHelper
import java.text.SimpleDateFormat
import java.util.*


/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:22
 */
const val SET_THEME = "set_theme"
const val REFRESH_BALANCE = "refresh_balance" //购买产品，提现等操作 刷新余额

//获取包名
fun Context.packageInfo(): PackageInfo = this.packageManager.getPackageInfo(this.packageName, 0)

//获取颜色
fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)
fun View.color(colorRes: Int) = context.color(colorRes)

//设置颜色
fun Context.text(textRes: Int) = this.resources.getString(textRes)
fun View.text(textRes: Int) = context.text(textRes)

//获取主题属性id
fun TypedValue.resourceId(resId: Int, theme: Resources.Theme): Int {
    theme.resolveAttribute(resId, this, true)
    return this.resourceId
}

//加载子布局
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = true): View {
    if (layoutId == -1) {
        return this
    }
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/*inline fun <reified T : ViewModel> NavController.viewModel(@NavigationRes navGraphId: Int): T {
    val storeOwner = getViewModelStoreOwner(navGraphId)
    return ViewModelProvider(storeOwner)[T::class.java]
}*/

fun String?.htmlToSpanned() =
    if (this.isNullOrEmpty()) "" else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

/***
 * 设置延迟时间的View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @return T
 */
fun <T : View> T.withTrigger(delay: Long = 600): T {
    triggerDelay = delay
    return this
}

/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {
    block(it as T)
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(time: Long = 600, block: (T) -> Unit) {
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else -601
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else 600
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}

/***
 * 带延迟过滤的点击事件监听，见[View.OnClickListener]
 * 延迟时间根据triggerDelay获取：600毫秒，不能动态设置
 */
interface OnLazyClickListener : View.OnClickListener {

    override fun onClick(v: View?) {
        if (v?.clickEnable() == true) {
            onLazyClick(v)
        }
    }

    fun onLazyClick(v: View)
}

/**
 * 设置Activity的亮度
 * @param [brightness] 0 ~ 1
 */
fun Activity.setBrightness(brightness: Float) {
    val attributes = window.attributes
    attributes.screenBrightness = brightness
    window.attributes = attributes
}

/**
 * 弹出软键盘
 */
fun View.showSoftInput() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * 隐藏软键盘
 */
fun View.hideSoftInput() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Long.toDateTime(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

/**
 * 是否登录，如果登录了就执行then，没有登录就直接跳转登录界面
 * @return true-已登录，false-未登录
 */
/*fun Context.checkLogin(then: (() -> Unit)? = null): Boolean {
    return if (isLogin()) {
        then?.invoke()
        true
    } else {
        this.startActivity<LoginActivity>()
        false
    }
}*/

fun isLogin(): Boolean = getLoginState() && CookieClass.hasCookie()

fun String.clickToCopy(context: Context) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val newPlainText = ClipData.newPlainText(null, this)
    clipboardManager.setPrimaryClip(newPlainText)
    SmartToast.showInCenter(R.string.copy_bord_content)
    TipHelper.Vibrate(context, longArrayOf(200, 300), false)
}

fun String.compareVersionCode(serverVersion: String): Boolean {
    return getVersionCode(this) < getVersionCode(serverVersion)
}

fun getVersionCode(code: String?): Float {
    if (code.isNullOrEmpty()) {
        return 0f
    }

    val index = code.indexOf(".")
    if (index < 0) {

        return code.toFloat()
    }
    if (index == code.length - 1) {
        return code.substring(0, index).toFloat()
    }
    val prefix = code.substring(0, index + 1)
    val suffix = code.substring(index + 1).replace(".", "")
    return prefix.plus(suffix).toFloat()
}

//清除长按时的toast

fun BottomNavigationView.clearToast(ids: List<Int>) {

    val bottomNavigationMenuView: ViewGroup = (getChildAt(0) as ViewGroup)
    //遍历子View,重写长按点击事件   
    for (position in ids.indices) {
        bottomNavigationMenuView.getChildAt(position).findViewById<View>(ids[position])
            .setOnLongClickListener { true }
    }
}

