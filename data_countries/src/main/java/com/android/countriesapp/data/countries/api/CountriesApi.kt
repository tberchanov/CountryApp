package com.android.countriesapp.data.countries.api

import com.android.countriesapp.data.countries.model.Country
import retrofit2.http.GET

internal interface CountriesApi {

    @GET("rest/v2/regionalbloc/eu")
    suspend fun loadCountries(): List<Country>
}