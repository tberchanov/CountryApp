package com.android.countriesapp.data.countries.repository

import com.android.countriesapp.data.countries.model.Country

interface CountriesRepository {

    suspend fun loadCountries(): List<Country>
}