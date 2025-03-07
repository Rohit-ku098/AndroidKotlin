package com.example.koin_di.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

// Qualifier is used to provide information that which implementation should be used.

class User(private val fname: String, private val lname: String) {
    fun getUser() = "$fname $lname"
}

fun provideFname() = "Rohit"
fun provideLname() = "Kumar"

val userModule = module {
    factory(named("fname")) { provideFname() }
    factory(named("lname")) { provideLname() }
    factory { User( get(named("fname")), get(named("lname")) ) }
}

