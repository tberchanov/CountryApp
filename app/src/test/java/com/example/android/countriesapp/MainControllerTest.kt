package com.example.android.countriesapp

import com.android.countriesapp.domain.countries.createLoadCountriesUseCase
import com.android.countriesapp.domain.countries.model.Country
import com.android.countriesapp.ui.MainController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainControllerTest {

    private lateinit var controller: MainController

    private val loadCountriesUse = createLoadCountriesUseCase()

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        controller = MainController(loadCountriesUse)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @Test
    fun load_countries_success() = runBlocking {
        val countries = listOf<Country>(Mockito.mock(Country::class.java))
        Mockito.`when`(loadCountriesUse.execute()).thenReturn(countries)
        controller.loadCountries { actualCountries ->
            Assert.assertEquals(countries, actualCountries)
        }
    }
}