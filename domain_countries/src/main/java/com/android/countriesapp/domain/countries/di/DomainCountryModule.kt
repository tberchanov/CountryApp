package com.android.countriesapp.domain.countries.di

import com.android.countriesapp.domain.countries.mapper.CountiesMapper
import com.android.countriesapp.domain.countries.usecase.LoadCountriesUseCase
import org.koin.dsl.module

val domainCountryModule = module {

    factory {
        CountiesMapper()
    }

    factory {
        LoadCountriesUseCase(get(), get())
    }
}