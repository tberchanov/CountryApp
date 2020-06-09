package com.android.countriesapp.data.countries

import com.android.countriesapp.data.countries.model.Country
import org.mockito.Mockito
import org.mockito.Mockito.`when`

object DefaultCountryData {
    const val name = "NAME"
    const val subregion = "SUBREGION"
    const val population = 42
    const val area = 777
}

fun createCountries(): List<Country> = listOf(
    createCountry()
)

fun createCountry(): Country = Mockito.mock(Country::class.java).also {
    `when`(it.name).thenReturn(DefaultCountryData.name)
    `when`(it.subregion).thenReturn(DefaultCountryData.subregion)
    `when`(it.population).thenReturn(DefaultCountryData.population)
    `when`(it.area).thenReturn(DefaultCountryData.area)
}