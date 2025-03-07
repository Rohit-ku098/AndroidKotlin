package com.example.koin_di.demo

import android.util.Log

class Car(private val engine: Engine, private val wheel: Wheel) {
    fun getCar() {
        engine.getEngine()
        wheel.getWheel()
        Log.d("Koin-DI-Learning", "Car is running...")
    }
}

class Engine() {
    fun getEngine() {
        Log.d("Koin-DI-Learning", "Engine is running...")
    }
}


class Wheel() {
    fun getWheel() {
        Log.d("Koin-DI-Learning", "Wheel is running...")
    }
}