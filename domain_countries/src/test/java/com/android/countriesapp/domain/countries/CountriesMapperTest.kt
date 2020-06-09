package com.android.countriesapp.domain.countries

import com.android.countriesapp.data.countries.DefaultCountryData
import com.android.countriesapp.data.countries.createCountries
import com.android.countriesapp.domain.countries.mapper.CountiesMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountriesMapperTest {

    private lateinit var mapper: CountiesMapper

    @Before
    fun init() {
        mapper = CountiesMapper()
    }

    @Test
    fun test_map() {
        val mockCountries = createCountries()

        val actualCountry = mapper.map(mockCountries).first()

        assertEquals(DefaultCountryData.name, actualCountry.name)
        assertEquals(DefaultCountryData.subregion, actualCountry.subregion)
        assertEquals(DefaultCountryData.area, actualCountry.area)
        assertEquals(DefaultCountryData.population, actualCountry.population)
    }
}