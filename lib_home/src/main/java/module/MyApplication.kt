package module

import com.whdx.base.app.BaseApplication
import com.whdx.data.di.dataSourceModule
import com.whdx.home.di.homeRepositoryModule
import com.whdx.home.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @Description
 * @Author dinghui
 * @Date 2020/9/28 0028 14:53
 */
class MyApplication : BaseApplication(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MyApplication)
            modules(homeModule)
        }
    }
}

val homeModule= listOf(
    homeViewModelModule, homeRepositoryModule, dataSourceModule
)