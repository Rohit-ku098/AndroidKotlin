package com.example.koin_di

import android.app.Application
import com.example.koin_di.di.demoModule
import com.example.koin_di.di.interfaceModule
import com.example.koin_di.di.userModule
import com.example.koin_di.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application (){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(demoModule, interfaceModule, viewModelModule, userModule)
        }
    }
}