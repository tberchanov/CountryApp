package com.android.countriesapp.data.countries.model

import com.google.gson.annotations.SerializedName

data class Languages(
    @SerializedName("iso639_1") val iso639_1: String,
    @SerializedName("iso639_2") val iso639_2: String,
    @SerializedName("name") val name: String,
    @SerializedName("nativeName") val nativeName: String
)