package com.example.koin_di.di

import com.example.koin_di.demo.Car
import com.example.koin_di.demo.Engine
import com.example.koin_di.demo.Wheel
import org.koin.core.module.Module
import org.koin.dsl.module

val demoModule: Module = module {
    factory { Car(get(), get()) }
    factory { Wheel() }
    factory { Engine() }
}