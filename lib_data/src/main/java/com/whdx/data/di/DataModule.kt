package com.whdx.data.di

import com.whdx.data.respository.base.RemoteDataSource
import org.koin.dsl.module

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/28 0028 14:39
 */
val dataSourceModule = module {
    single { RemoteDataSource() }
}