package com.android.countriesapp.ui

import com.android.countriesapp.domain.countries.model.Country
import com.android.countriesapp.domain.countries.usecase.LoadCountriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainController(
    private val loadCountriesUseCase: LoadCountriesUseCase
) {

    suspend fun loadCountries(
        block: (List<Country>) -> Unit
    ) = withContext(Dispatchers.IO) {
        val countries = loadCountriesUseCase.execute()
        withContext(Dispatchers.Main) {
            block.invoke(countries)
        }
    }
}