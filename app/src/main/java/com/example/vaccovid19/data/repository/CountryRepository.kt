package com.example.vaccovid19.data.repository

import com.example.vaccovid19.data.CountryDataSource

class CountryRepository(
    private val countryDataSource: CountryDataSource
) {

    suspend fun getCountriesInContinent(continent: String) =
        countryDataSource.getCountryInContinent(continent)

    suspend fun getCountryPerDay(symbol: String) =
        countryDataSource.getCountryPerDay(symbol)
}
