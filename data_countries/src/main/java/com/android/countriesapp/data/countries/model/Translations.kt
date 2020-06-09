package com.android.countriesapp.data.countries.model

import com.google.gson.annotations.SerializedName

data class Translations(
    @SerializedName("de") val de: String,
    @SerializedName("es") val es: String,
    @SerializedName("fr") val fr: String,
    @SerializedName("ja") val ja: String,
    @SerializedName("it") val it: String,
    @SerializedName("br") val br: String,
    @SerializedName("pt") val pt: String,
    @SerializedName("nl") val nl: String,
    @SerializedName("hr") val hr: String,
    @SerializedName("fa") val fa: String
)