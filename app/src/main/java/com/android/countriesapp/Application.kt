package com.android.countriesapp

import android.app.Application
import com.android.countriesapp.data.countries.di.countriesDataModule
import com.android.countriesapp.di.mainModule
import com.android.countriesapp.domain.countries.di.domainCountryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@Application)
            modules(
                countriesDataModule,
                domainCountryModule,
                mainModule
            )
        }
    }
}