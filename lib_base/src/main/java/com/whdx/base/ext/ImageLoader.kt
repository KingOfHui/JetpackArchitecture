package com.whdx.base.ext

import android.app.Activity
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.whdx.base.R

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:22
 */
fun ImageView.loadImage(
    fragment: Fragment? = null,
    activity: Activity? = null,
    url: String? = null,
    imageOptions: ImageOptions? = null
) {
    when {
        fragment != null -> Glide.with(fragment)
        activity != null -> Glide.with(activity)
        else -> Glide.with(this)
    }.load(url)
        .apply(requestOptions(imageOptions))
        .transition(
            DrawableTransitionOptions.with(
                DrawableCrossFadeFactory
                    .Builder(300)
                    .setCrossFadeEnabled(true)
                    .build()
            )
        )
        .into(this)
}

private fun requestOptions(imageOptions: ImageOptions?) = RequestOptions().apply {
    imageOptions?.let {
        transform(RoundedCorners(it.cornersRadius))
        placeholder(it.placeholder)
        error(it.error)
        fallback(it.fallback)
        if (it.circleCrop) {
            circleCrop()
        }
    }
}

class ImageOptions {
    var placeholder = R.drawable.emotion_success         // 加载占位图
    var error = R.drawable.emotion_error               // 错误占位图
    var fallback = R.drawable.emotion_fail            // null占位图
    var cornersRadius = 8       // 圆角半径
    var circleCrop = false      // 是否裁剪为圆形
}