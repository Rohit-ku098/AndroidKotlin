package com.example.koin_di.demo

import android.util.Log

interface DemoOneInterface {
    fun getDemoOne()
}

interface DemoTwoInterface {
    fun getDemoTwo()
}

class DemoImpl: DemoOneInterface, DemoTwoInterface{
    override fun getDemoOne() {
        Log.d("Koin-DI-Learning", "DemoOne executed...")
    }

    override fun getDemoTwo() {
        Log.d("Koin-DI-Learning", "DemoTwo executed...")
    }
}

class TestDemoInterface(private val demoOneInterface: DemoOneInterface, private val demoTwoInterface: DemoTwoInterface) {
    fun getTestDemoInterface() {
        demoOneInterface.getDemoOne()
        demoTwoInterface.getDemoTwo()
    }
}