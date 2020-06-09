package com.android.countriesapp.data.countries.di

import com.android.countriesapp.data.countries.api.CountriesApi
import com.android.countriesapp.data.countries.repository.CountriesRepository
import com.android.countriesapp.data.countries.repository.RemoteCountriesRepository
import com.android.countriesapp.data.countries.setup.RetrofitBuilder
import org.koin.dsl.module
import retrofit2.Retrofit

val countriesDataModule = module {

    single {
        RetrofitBuilder.getRetrofit()
    }

    single {
        get<Retrofit>().create(CountriesApi::class.java)
    }

    single<CountriesRepository> {
        RemoteCountriesRepository(get())
    }
}