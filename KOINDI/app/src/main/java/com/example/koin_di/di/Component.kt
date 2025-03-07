package com.example.koin_di.di

import com.example.koin_di.demo.Car
import com.example.koin_di.demo.TestDemoInterface
import com.example.koin_di.viewmodel.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Component: KoinComponent {
    val car: Car by inject()
    val testDemoInterface: TestDemoInterface by inject()
    val mainViewModel: MainViewModel by inject()
}