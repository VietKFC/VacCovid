package com.example.vaccovid19.data.model

import com.google.gson.annotations.SerializedName

data class CountryPerDay(
    @SerializedName("total_cases")
    val totalCase: Int,
    @SerializedName("date")
    val date: String
)
