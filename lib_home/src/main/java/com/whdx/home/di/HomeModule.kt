package com.whdx.home.di

import com.whdx.data.respository.base.LocalDataSource
import com.whdx.data.respository.UserRepository
import com.whdx.data.respository.UserViewModel
import com.whdx.home.vm.*
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
    viewModel { MineViewModel(get()) }
    viewModel { SelectCloudViewModel(get()) }
    viewModel { MyCloudViewModel(get()) }
    viewModel { RankViewModel(get()) }
    viewModel { WalletViewModel(get()) }
    viewModel { NetDataViewModel(get()) }
    viewModel { MyWalletViewModel(get()) }
    viewModel { RecordChongZhiViewModel(get()) }
    viewModel { RecordWithdrawViewModel(get()) }
    viewModel { BonusViewModel(get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { MyTotalBonusViewModel(get()) }
}

val homeRepositoryModule = module {
    single { UserRepository(get()) }
    single { LocalDataSource() }
}