package com.android.countriesapp.domain.countries

import com.android.countriesapp.domain.countries.usecase.LoadCountriesUseCase
import org.mockito.Mockito

fun createLoadCountriesUseCase() = Mockito.mock(LoadCountriesUseCase::class.java)