package com.whdx.home.di

import com.whdx.data.respository.UserRepository
import com.whdx.data.respository.UserViewModel
import com.whdx.data.respository.base.RemoteDataSource
import com.whdx.home.HomeViewModel
import com.whdx.home.MineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/28 0028 14:37
 */
val homeViewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MineViewModel() }
}

val homeRepositoryModule = module {
    single { UserRepository(get()) }
}