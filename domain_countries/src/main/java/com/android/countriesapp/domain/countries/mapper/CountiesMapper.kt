package com.android.countriesapp.domain.countries.mapper

import com.android.countriesapp.domain.countries.model.Country
import com.android.countriesapp.data.countries.model.Country as CountryRemote

class CountiesMapper {

    fun map(
        countiesRemote: List<CountryRemote>
    ): List<Country> = countiesRemote.map {
        Country(it.name, it.subregion, it.population, it.area)
    }
}