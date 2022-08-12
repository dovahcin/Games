package com.android.games

import android.app.Application
import com.android.games.di.databaseModule
import com.android.games.di.mainModule
import com.android.games.di.networkModule
import com.android.games.di.stateModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }
    private fun initializeKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(
                networkModule,
                mainModule,
                stateModule,
                databaseModule
            ))
        }
    }

}