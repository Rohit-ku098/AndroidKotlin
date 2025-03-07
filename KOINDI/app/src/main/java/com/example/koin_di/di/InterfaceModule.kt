package com.example.koin_di.di

import com.example.koin_di.demo.DemoImpl
import com.example.koin_di.demo.DemoOneInterface
import com.example.koin_di.demo.DemoTwoInterface
import com.example.koin_di.demo.TestDemoInterface
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

val interfaceModule = module {
//    factory { DemoImpl() as DemoOneInterface }
//    factory<DemoOneInterface>{ DemoImpl() }

//    factory { DemoImpl() } bind DemoOneInterface::class

    factory { DemoImpl() } binds arrayOf(DemoOneInterface::class, DemoTwoInterface::class)
    factory { TestDemoInterface(get(), get()) }
}