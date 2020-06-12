package com.android.countriesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.countriesapp.R
import com.android.countriesapp.domain.countries.model.Country
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainController: MainController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            mainController.loadCountries(::showCountries)
        }
    }

    private fun showCountries(countries: List<Country>) {
        countriesRecyclerView.adapter =
            CountriesAdapter(countries)
    }
}