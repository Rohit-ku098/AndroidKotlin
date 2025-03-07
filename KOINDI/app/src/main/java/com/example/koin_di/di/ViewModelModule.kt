package com.example.koin_di.di

import com.example.koin_di.viewmodel.MainViewModel
import com.example.koin_di.viewmodel.ViewModelTest
import com.example.koin_di.viewmodel.ViewModelTestImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {
    factory { ViewModelTestImpl() } bind ViewModelTest::class
    viewModel { MainViewModel(get()) }
}