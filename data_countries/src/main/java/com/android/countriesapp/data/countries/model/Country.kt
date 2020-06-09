package com.android.countriesapp.data.countries.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: String,
    @SerializedName("topLevelDomain") val topLevelDomain: List<String>,
    @SerializedName("alpha2Code") val alpha2Code: String,
    @SerializedName("alpha3Code") val alpha3Code: String,
    @SerializedName("callingCodes") val callingCodes: List<Int>,
    @SerializedName("capital") val capital: String,
    @SerializedName("altSpellings") val altSpellings: List<String>,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("population") val population: Int,
    @SerializedName("latlng") val latlng: List<Double>,
    @SerializedName("demonym") val demonym: String,
    @SerializedName("area") val area: Int,
    @SerializedName("gini") val gini: String,
    @SerializedName("timezones") val timezones: List<String>,
    @SerializedName("borders") val borders: List<String>,
    @SerializedName("nativeName") val nativeName: String,
    @SerializedName("numericCode") val numericCode: Int,
    @SerializedName("currencies") val currencies: List<Currencies>,
    @SerializedName("languages") val languages: List<Languages>,
    @SerializedName("translations") val translations: Translations,
    @SerializedName("flag") val flag: String,
    @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBlocs>,
    @SerializedName("cioc") val cioc: String
)