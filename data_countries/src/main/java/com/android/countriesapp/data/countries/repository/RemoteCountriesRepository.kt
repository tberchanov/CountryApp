package com.android.countriesapp.data.countries.repository

import com.android.countriesapp.data.countries.api.CountriesApi

internal class RemoteCountriesRepository(
    private val countriesApi: CountriesApi
) : CountriesRepository {

    override suspend fun loadCountries() = countriesApi.loadCountries()
}