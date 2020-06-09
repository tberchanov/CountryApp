package com.android.countriesapp.domain.countries.usecase

import com.android.countriesapp.data.countries.repository.CountriesRepository
import com.android.countriesapp.domain.countries.mapper.CountiesMapper
import com.android.countriesapp.domain.countries.model.Country

class LoadCountriesUseCase(
    private val countriesRepository: CountriesRepository,
    private val countiesMapper: CountiesMapper
) {

    suspend fun execute(): List<Country> =
        countriesRepository.loadCountries()
            .let(countiesMapper::map)
}