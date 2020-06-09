package com.android.countriesapp.domain.countries.model

data class Country(
    val name: String,
    val subregion: String,
    val population: Int,
    val area: Int
)