package com.whdx.base.util.navigation

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.wwy.android.ui.base.BaseActivity

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/29 0029 11:19
 */
fun BaseActivity.setupWithKeepStateNav(
    @IdRes viewId: Int,
    @NavigationRes graphResId: Int
) {
    val navController = findNavController(viewId)

    val navHostFragment =
        supportFragmentManager.findFragmentById(viewId)!!

    val navigator = KeepStateNavHostFragment.NormalNavigator(
        this,
        navHostFragment.childFragmentManager,
        viewId
    )
    navController.navigatorProvider.addNavigator(navigator)
    navController.setGraph(graphResId)
}

fun BaseActivity.setupBottomNavigationViewWithKeepStateNav(
    @IdRes viewId: Int,
    @NavigationRes graphResId: Int
): NavController {
    val navController = findNavController(viewId)

    val navHostFragment =
        supportFragmentManager.findFragmentById(viewId)!!

    val navigator = KeepStateNavigator(
        this,
        navHostFragment.childFragmentManager,
        viewId
    )
    navController.navigatorProvider.addNavigator(navigator)
    navController.setGraph(graphResId)

    return navController
}