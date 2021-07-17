package com.example.vaccovid19.data

import com.example.vaccovid19.data.model.Country
import com.example.vaccovid19.data.model.CountryPerDay

interface CountryDataSource {

    suspend fun getCountryInContinent(continent: String): List<Country>

    suspend fun getCountryPerDay(symbol: String): List<CountryPerDay>
}
