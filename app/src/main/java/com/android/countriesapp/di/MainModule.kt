package com.android.countriesapp.di

import com.android.countriesapp.ui.MainController
import org.koin.dsl.module

val mainModule = module {

    factory { MainController(get()) }
}