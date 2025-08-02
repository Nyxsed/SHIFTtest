package ru.nyxsed.shifttest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.nyxsed.shifttest.di.databaseModule
import ru.nyxsed.shifttest.di.networkModule
import ru.nyxsed.shifttest.di.repositoryModule
import ru.nyxsed.shifttest.di.useCaseModule
import ru.nyxsed.shifttest.di.userInfoModule
import ru.nyxsed.shifttest.di.userListModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                useCaseModule,
                repositoryModule,
                userListModule,
                userInfoModule,
            )
        }
    }
}