package com.android.countriesapp.data.countries

import com.android.countriesapp.data.countries.api.CountriesApi
import com.android.countriesapp.data.countries.repository.CountriesRepository
import com.android.countriesapp.data.countries.repository.RemoteCountriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RemoteCountriesRepositoryTest {

    @Mock
    private lateinit var countriesApi: CountriesApi

    private lateinit var remoteCountriesRepository: CountriesRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        remoteCountriesRepository = RemoteCountriesRepository(countriesApi)
    }

    @Test
    fun test_loadCounties() = runBlocking {
        val mockCounties = createCountries()
        `when`(countriesApi.loadCountries()).thenReturn(mockCounties)

        val actualCountries = remoteCountriesRepository.loadCountries()

        Mockito.verify(countriesApi).loadCountries()
        assertEquals(mockCounties, actualCountries)
    }
}