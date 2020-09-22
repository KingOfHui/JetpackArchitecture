package com.whdx.pen

import com.whdx.data.respository.UserRepository
import com.whdx.data.respository.UserViewModel
import com.whdx.data.respository.base.RemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @ClassName AppModule
 * @Description
 * @Author dinghui
 * @Date 2020/9/22 0018 16:33
 */
val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}

val repositoryModule = module {
    single { RemoteDataSource() }
    single { UserRepository(get()) }
}

val appModule = listOf(
    viewModelModule,
    repositoryModule
)
