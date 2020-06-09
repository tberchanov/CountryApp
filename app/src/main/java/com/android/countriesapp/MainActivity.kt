package com.android.countriesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.countriesapp.domain.countries.model.Country
import com.android.countriesapp.domain.countries.usecase.LoadCountriesUseCase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val loadCountriesUseCase: LoadCountriesUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            val countries = loadCountriesUseCase.execute()
            showCountries(countries)
        }
    }

    private suspend fun showCountries(
        countries: List<Country>
    ) = withContext(Dispatchers.Main) {
        countriesRecyclerView.adapter = CountriesAdapter(countries)
    }
}