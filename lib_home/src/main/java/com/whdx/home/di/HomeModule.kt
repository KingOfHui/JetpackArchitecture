package com.whdx.home.di

import com.whdx.data.db.LocalDataSource
import com.whdx.data.respository.UserRepository
import com.whdx.data.respository.UserViewModel
import com.whdx.home.vm.HomeViewModel
import com.whdx.home.vm.MineViewModel
import com.whdx.home.vm.SelectCloudViewModel
import com.whdx.home.vm.WalletViewModel
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
    viewModel { SelectCloudViewModel() }
    viewModel { WalletViewModel(get()) }
}

val homeRepositoryModule = module {
    single { UserRepository(get()) }
    single { LocalDataSource() }
}