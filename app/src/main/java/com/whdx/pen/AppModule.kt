package com.whdx.pen

import com.whdx.data.di.dataSourceModule
import com.whdx.home.di.homeRepositoryModule
import com.whdx.home.di.homeViewModelModule

/**
 * @ClassName AppModule
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:33
 */

val appModule = listOf(
    dataSourceModule,
    homeViewModelModule,
    homeRepositoryModule
)
